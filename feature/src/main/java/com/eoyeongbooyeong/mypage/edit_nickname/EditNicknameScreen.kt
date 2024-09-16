package com.eoyeongbooyeong.mypage.edit_nickname

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
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
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
fun EditNicknameRoute(
    navigateUp: () -> Unit,
    viewModel: EditNicknameViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    EditNicknameSideEffect.NavigateUp -> navigateUp()
                }
            }
    }

    EditNicknameScreen(
        nickname = state.value.nickname,
        isAvailable = state.value.isAvailable,
        isError = state.value.isError,
        onNicknameChanged = viewModel::updateName,
        onCheckNickname = viewModel::isAvailableNickname,
        onSetNewNickname = viewModel::setNewNickname,
        navigateUp = navigateUp
    )
}

@Composable
fun EditNicknameScreen(
    nickname: String,
    isAvailable: Boolean,
    isError: Boolean,
    onNicknameChanged: (String) -> Unit = {},
    onCheckNickname: () -> Unit = {},
    onSetNewNickname: (String) -> Unit = {},
    navigateUp: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .addFocusCleaner(focusManager)
            .background(White)
    ) {
        BooTextTopAppBar(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                    contentDescription = "left",
                    modifier = Modifier.noRippleClickable(onClick = navigateUp)
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
                        value = nickname,
                        placeholder = "닉네임을 입력해 주세요",
                        onTextChanged = onNicknameChanged,
                        onFocusChanged = {},
                        modifier = Modifier.weight(1f),
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    BooEnabledButton(
                        text = "중복 확인",
                        enabled = nickname.isNotBlank(),
                        onClick = onCheckNickname
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (isError) "이미 사용 중인 닉네임입니다." else if (isAvailable) "사용 가능한 닉네임입니다!" else " ",
                    style = BooTheme.typography.caption2,
                    color = if (isError) Red else Blue300
                )
            }

            Spacer(modifier = Modifier.weight(5f))

            BooEnabledButton(
                text = "변경하기",
                modifier = Modifier.fillMaxWidth(),
                enabled = isAvailable,
                onClick = { onSetNewNickname(nickname) }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun PreviewEditNickNameScreen() {
    BooTheme {
        EditNicknameScreen(
            nickname = "닉네임",
            isAvailable = true,
            isError = false
        )
    }
}
