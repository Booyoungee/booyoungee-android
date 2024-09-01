package com.eoyeongbooyeong.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToHome: (Boolean) -> Unit,
    navigateToLogIn: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        delay(2000L)
        navigateToHome(true)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Splash Screen")
    }
}
