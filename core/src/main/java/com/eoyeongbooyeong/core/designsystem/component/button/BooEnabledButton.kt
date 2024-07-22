package com.eoyeongbooyeong.core.designsystem.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
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
        modifier = modifier.padding(horizontal = 22.dp, vertical = 17.dp),
        enabled = enabled,
        backgroundColor = if (enabled) Blue400 else Gray100,
        textColor = if (enabled) Gray300 else White,
        textStyle = textStyle
    )
}
