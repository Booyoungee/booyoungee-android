package com.eoyeongbooyeong.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Blue400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
fun BooBaseButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = Blue400,
    textColor: Color = White,
    textStyle: TextStyle = TextStyle.Default,
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .run {
                if (enabled) noRippleClickable(onClick = onClick)
                else this
            }
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor
        )
    }
}

@Composable
fun BooLargeButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = Blue400,
    textColor: Color = White,
    textStyle: TextStyle = TextStyle.Default,
    onClick: () -> Unit = {},
) {
    BooBaseButton(
        text = text,
        modifier = modifier.padding(horizontal = 22.dp, vertical = 17.dp),
        enabled = enabled,
        backgroundColor = backgroundColor,
        textColor = textColor,
        textStyle = textStyle,
        onClick = onClick
    )
}

@Composable
fun BooMiniButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Blue400,
    textColor: Color = White,
    textStyle: TextStyle = TextStyle.Default,
    onClick: () -> Unit = {}
) {
    BooBaseButton(
        text = text,
        onClick = onClick,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 14.5.dp),
        backgroundColor = backgroundColor,
        textColor = textColor,
        textStyle = textStyle
    )
}
