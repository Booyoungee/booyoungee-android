package com.eoyeongbooyeong.core.designsystem.component.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun BooGradientButton(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onClick: () -> Unit = {},
) {
    val colorStops = arrayOf(
        0.0f to Purple,
        1f to Color(0xFF4067E5)
    )
    BooLargeButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        textColor = White,
        backgroundGradient = Brush.horizontalGradient(colorStops = colorStops),
        textStyle = textStyle
    )
}

@Preview(showBackground = true)
@Composable
fun BooGradientButtonPreview() {
    BooGradientButton(text = "Gradient Button")
}
