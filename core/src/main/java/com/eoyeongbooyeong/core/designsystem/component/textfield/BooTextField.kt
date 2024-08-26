package com.eoyeongbooyeong.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.Gray300

@Composable
fun BooTextField(
    value: String,
    placeholder: String,
    onTextChanged: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = value,
        onValueChange = onTextChanged,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Gray100)
            .padding(horizontal = 10.dp, vertical = 16.dp)
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                onFocusChanged(focusState.isFocused)
            },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            },
            onSearch = {
                focusManager.clearFocus()
            }
        ),
        // textStyle = HankkiTheme.typography.body1.copy(color = textColor) // typography 설정 후 copy를 통해 글자 색 설정
        decorationBox = { innerTextField ->
            Box {
                innerTextField()
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Gray300,
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun BooTextFieldPreview() {
    Column {
        BooTextField(
            value = "",
            placeholder = "Text Field",
            onTextChanged = {},
            onFocusChanged = {},
        )

        Spacer(modifier = Modifier.height(16.dp))

        BooTextField(
            value = "Text Entered",
            placeholder = "Placeholder",
            onTextChanged = {},
            onFocusChanged = {},
        )
    }

}

