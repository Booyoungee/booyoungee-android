package com.eoyeongbooyeong.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.designsystem.component.button.BooEnabledButton
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooTextField
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Red
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.addFocusCleaner
import timber.log.Timber

@Composable
internal fun SignUpRoute(
    navigateToHome: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSideEffect.NavigateToHome -> navigateToHome()
                    is SignUpSideEffect.SignUpError -> {
                        Timber.e(sideEffect.errorMessage)
                    }
                }
            }
    }

    SignUpScreen(
        name = state.name,
        isAvailable = state.isAvailable,
        isError = state.isError,
        onTextChanged = viewModel::updateName,
        isAvailableNickname = viewModel::isAvailableNickname,
        signUp = viewModel::signUp,
    )
}

@Composable
internal fun SignUpScreen(
    name: String,
    isAvailable: Boolean,
    isError: Boolean,
    onTextChanged: (String) -> Unit,
    isAvailableNickname: () -> Unit,
    signUp: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
            .background(White)
            .statusBarsPadding(),
    ) {
        BooTextTopAppBar(text = "회원가입")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier.weight(64f / 494f))

            Text(
                text = "반가워요!\n앞으로 어떻게 불러드릴까요?",
                style = BooTheme.typography.head3,
            )

            Spacer(modifier = Modifier.height(28.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BooTextField(
                        value = name,
                        placeholder = "닉네임을 입력해 주세요",
                        onTextChanged = onTextChanged,
                        onFocusChanged = {},
                        modifier = Modifier.weight(1f),
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    BooEnabledButton(
                        text = "중복 확인",
                        enabled = name.isNotBlank(),
                        onClick = isAvailableNickname
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (isError) "이미 사용 중인 닉네임입니다." else if (isAvailable) "사용 가능한 닉네임입니다!" else " ",
                    style = BooTheme.typography.caption2,
                    color = if (isError) Red else Blue300
                )
            }

            Spacer(modifier = Modifier.weight(326f / 494f))

            BooEnabledButton(
                text = "회원가입 완료하기",
                modifier = Modifier.fillMaxWidth(),
                enabled = isAvailable,
                onClick = signUp,
            )

            Spacer(modifier = Modifier.weight(104f / 494f))
        }
    }
}
