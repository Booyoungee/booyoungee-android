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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eoyeongbooyeong.auth.login.LoginViewModel
import com.eoyeongbooyeong.core.designsystem.component.button.BooEnabledButton
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooTextField
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Red
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
internal fun SignUpRoute(
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    SignUpScreen()
}

@Composable
internal fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .statusBarsPadding(),
    ) {
        BooTextTopAppBar(text = "회원가입")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier.weight(64f/494f))

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
                        value = "",
                        placeholder = "닉네임을 입력해 주세요",
                        onTextChanged = {},
                        onFocusChanged = {},
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    BooEnabledButton(
                        text = "중복 확인",
                        enabled = false, // BooTextField is NotEmpty
                    ) {

                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (true) { // state : isError
                    Text(
                        text = "이미 사용 중인 닉네임입니다.",
                        style = BooTheme.typography.caption2,
                        color = Red
                    )
                }

                if (false) { // state : isAvailable
                    Text(
                        text = "사용 가능한 닉네임입니다!",
                        style = BooTheme.typography.caption2,
                        color = Blue300
                    )
                }
            }

            Spacer(modifier = Modifier.weight(326f/494f))

            BooEnabledButton(
                text = "회원가입 완료하기",
                modifier = Modifier.fillMaxWidth(),
                enabled = true // state : isAvailable
            )

            Spacer(modifier = Modifier.weight(104f/494f))
        }
    }
}
