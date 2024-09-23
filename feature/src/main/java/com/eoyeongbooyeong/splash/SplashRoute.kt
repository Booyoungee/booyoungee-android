package com.eoyeongbooyeong.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.designsystem.theme.Blue100
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.main.ui.theme.BooyoungeeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SplashRoute(
    navigateToHome: () -> Unit,
    navigateToLogIn: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = Unit) {
        viewModel.showSplash()
    }

    val systemUiController = rememberSystemUiController()
    val customColor = Blue100

    DisposableEffect(systemUiController) {
        systemUiController.setSystemBarsColor(
            color = customColor,
            darkIcons = true
        )

        systemUiController.setNavigationBarColor(
            color = customColor,
            darkIcons = true
        )

        onDispose {
            systemUiController.setStatusBarColor(
                color = White,
                darkIcons = true,
                transformColorForLightContent = { Gray400 }
            )
            systemUiController.setNavigationBarColor(
                color = White,
                darkIcons = true,
                navigationBarContrastEnforced = false
            )
        }
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SplashSideEffect.NavigateToHome -> navigateToHome()
                    is SplashSideEffect.NavigateToLogin -> navigateToLogIn()
                }
            }
    }

    SplashScreen()
}

@Composable
fun SplashScreen(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue100),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_splash_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f)
        )
    }
}
