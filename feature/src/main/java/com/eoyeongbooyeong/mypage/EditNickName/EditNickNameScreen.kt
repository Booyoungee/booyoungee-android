package com.eoyeongbooyeong.mypage.EditNickName

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.button.BooEnabledButton
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooTextField
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Red
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
fun EditNickNameRoute() {
    EditNickNameScreen()
}

@Composable
fun EditNickNameScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        BooTextTopAppBar(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                    contentDescription = "left",
                    modifier = Modifier.noRippleClickable {
                        // navigate Up
                    }
                )
            },
            text = "닉네임 변경"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BooTextField(
                        value = "",
                        placeholder = "새로운 닉네임을 입력해 주세요",
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

            Spacer(modifier = Modifier.weight(5f))

            BooEnabledButton(
                text = "변경하기",
                modifier = Modifier.fillMaxWidth(),
                enabled = true // state : isAvailable
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun PreviewEditNickNameScreen() {
    BooTheme {
        EditNickNameScreen()
    }
}
