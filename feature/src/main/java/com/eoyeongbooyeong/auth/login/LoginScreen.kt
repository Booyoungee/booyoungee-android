package com.eoyeongbooyeong.auth.login

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
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.feature.R

@Composable
internal fun LoginRoute(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is LoginSideEffect.NavigateToHome -> navigateToHome()
                    is LoginSideEffect.NavigateToSignUp -> navigateToSignUp()
                }
            }
    }

    LoginScreen(
        navigateToSignUp = viewModel::navigateToSignUp
    )
}

@Composable
internal fun LoginScreen(
    navigateToSignUp: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().background(White),
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
            modifier = Modifier.noRippleClickable {
                navigateToSignUp()
            }
        )

        Spacer(modifier = Modifier.weight(108f / 563f))
    }
}
