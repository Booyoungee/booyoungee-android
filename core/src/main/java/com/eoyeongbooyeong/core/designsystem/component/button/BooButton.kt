package com.eoyeongbooyeong.core.designsystem.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .run {
                if (enabled) clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                )
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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val color by animateColorAsState(
        if (isPressed) backgroundColor.copy(alpha = 0.8f) else backgroundColor,
        label = ""
    )

    BooBaseButton(
        text = text,
        modifier = modifier,
        enabled = enabled,
        paddingValues = paddingValues,
        backgroundColor = color,
        backgroundGradient = backgroundGradient,
        textColor = textColor,
        textStyle = textStyle,
        interactionSource = interactionSource,
        onClick = onClick
    )
}

@Composable
fun BooMiniButton(
    text: String,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
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