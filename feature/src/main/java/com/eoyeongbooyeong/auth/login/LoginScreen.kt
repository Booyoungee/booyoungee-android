package com.eoyeongbooyeong.auth.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.feature.R
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import timber.log.Timber

@Composable
internal fun LoginRoute(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is LoginSideEffect.NavigateToHome -> navigateToHome()
                    is LoginSideEffect.NavigateToSignUp -> navigateToSignUp()
                    is LoginSideEffect.LoginError -> {
                        Timber.e(sideEffect.errorMessage)
                    }

                    is LoginSideEffect.StartKakaoTalkLogin -> {
                        startKakaoTalkLogin(context) { token, error ->
                            viewModel.handleLoginResult(token, error)
                        }
                    }

                    is LoginSideEffect.StartKakaoWebLogin -> {
                        startKakaoWebLogin(context) { token, error ->
                            viewModel.handleLoginResult(token, error)
                        }
                    }
                }
            }
    }

    LoginScreen(
        startKakaoLogin = {
            viewModel.startKakaoLogin(
                isKakaoTalkAvailable = UserApiClient.instance.isKakaoTalkLoginAvailable(context)
            )
        }
    )
}

@Composable
internal fun LoginScreen(
    startKakaoLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(186f / 563f))

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )

        Spacer(modifier = Modifier.weight(269f / 563f))

        Image(
            imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.btn_kakao_login),
            contentDescription = "카카오 로그인 버튼",
            modifier = Modifier.noRippleClickable(
                onClick = startKakaoLogin
            )
        )

        Spacer(modifier = Modifier.weight(108f / 563f))
    }
}

private fun startKakaoTalkLogin(
    context: Context,
    handleLogin: (OAuthToken?, Throwable?) -> Unit,
) {
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        handleLogin(token, error)
    }
}

private fun startKakaoWebLogin(
    context: Context,
    handleLogin: (OAuthToken?, Throwable?) -> Unit,
) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        handleLogin(token, error)
    }
}

