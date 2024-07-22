package com.eoyeongbooyeong.core.designsystem.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Blue400
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.Gray300
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun BooEnabledButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = TextStyle.Default,
    onClick: () -> Unit = {},
) {
    BooLargeButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        backgroundColor = if (enabled) Blue400 else Gray100,
        textColor = if (enabled) White else Gray300,
        textStyle = textStyle
    )
}

@Preview
@Composable
fun BooEnabledButtonPreview() {
    Column {
        BooEnabledButton(text = "Enabled Button", enabled = true)
        Spacer(modifier = Modifier.height(20.dp))
        BooEnabledButton(text = "UnEnabled Button", enabled = false)
    }
}
