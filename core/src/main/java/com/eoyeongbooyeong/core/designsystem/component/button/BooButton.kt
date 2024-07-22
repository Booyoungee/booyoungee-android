package com.eoyeongbooyeong.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Blue400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
fun BooBaseButton(
    text: String,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    enabled: Boolean = true,
    backgroundColor: Color = Blue400,
    backgroundGradient: Brush? = null,
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
            .run {
                if (backgroundGradient != null) this.background(backgroundGradient)
                else this.background(backgroundColor)
            }
            .padding(paddingValues)

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
    paddingValues: PaddingValues = PaddingValues(horizontal = 22.dp, vertical = 17.dp),
    enabled: Boolean = true,
    backgroundColor: Color = Blue400,
    backgroundGradient: Brush? = null,
    textColor: Color = White,
    textStyle: TextStyle = TextStyle.Default,
    onClick: () -> Unit = {},
) {
    BooBaseButton(
        text = text,
        modifier = modifier,
        enabled = enabled,
        paddingValues = paddingValues,
        backgroundColor = backgroundColor,
        backgroundGradient = backgroundGradient,
        textColor = textColor,
        textStyle = textStyle,
        onClick = onClick
    )
}

@Composable
fun BooMiniButton(
    text: String,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 22.dp, vertical = 17.dp),
    backgroundColor: Color = Blue400,
    textColor: Color = White,
    textStyle: TextStyle = TextStyle.Default,
    onClick: () -> Unit = {},
) {
    BooBaseButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        paddingValues = paddingValues,
        backgroundColor = backgroundColor,
        textColor = textColor,
        textStyle = textStyle
    )
}

@Preview(showBackground = true)
@Composable
fun BooButtonsPreview() {
    Column {
        BooBaseButton(text = "Base Button")
        BooMiniButton(text = "Mini Button")
        BooLargeButton(text = "Large Button")
    }
}