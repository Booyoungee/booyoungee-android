package com.eoyeongbooyeong.splash

import android.util.Log
import androidx.compose.runtime.Composable

@Composable
fun SplashScreen(
    navigateToHome: (Boolean) -> Unit,
    navigateToLogIn: () -> Unit,
) {
    Log.d("SplashScreen", "SplashScreen")
    navigateToHome(true)
}