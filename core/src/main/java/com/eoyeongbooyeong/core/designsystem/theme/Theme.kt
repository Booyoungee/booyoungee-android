package com.eoyeongbooyeong.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme()

private val LocalBooTypography = staticCompositionLocalOf<BooTypography> {
    error("No BooTypography provided")
}

/* BooTheme
*
* Typo 변경 시 ex. BooTheme.typography.head1
* ex) Text(text = "Example Typo", style = BooTheme.typography.head1)
*/

object BooTheme {
    val typography: BooTypography
        @Composable get() = LocalBooTypography.current
}

@Composable
fun ProvideBooTypography(typography: BooTypography, content: @Composable () -> Unit) {
    val provideTypography = remember { typography.copy() }
    provideTypography.update(typography)
    CompositionLocalProvider(
        LocalBooTypography provides provideTypography,
        content = content
    )
}

@Composable
fun BooTheme(
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = LightColorScheme
    val typography = booTypography()

    // set status bar & navigation bar color
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = White.toArgb()
            window.navigationBarColor = White.toArgb()

            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = true
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightNavigationBars = true
        }
    }

    ProvideBooTypography(typography) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

@Preview
@Composable
fun BooThemePreview() {
    BooTheme {
        Column(modifier = Modifier.background(White)) {
            Text(text = "BooTheme", style = BooTheme.typography.head1)
            Text(text = "BooTheme", style = BooTheme.typography.head2)
            Text(text = "BooTheme", style = BooTheme.typography.head3)
            Text(text = "BooTheme", style = BooTheme.typography.body1)
            Text(text = "BooTheme", style = BooTheme.typography.body2)
            Text(text = "BooTheme", style = BooTheme.typography.body3)
            Text(text = "BooTheme", style = BooTheme.typography.body4)
            Text(text = "BooTheme", style = BooTheme.typography.caption1)
            Text(text = "BooTheme", style = BooTheme.typography.caption2)
            Text(text = "BooTheme", style = BooTheme.typography.caption3)
            Text(text = "BooTheme", style = BooTheme.typography.caption4)

            Text(text = "BooTheme", style = BooTheme.typography.head1.copy(color = Blue300))        }
    }
}