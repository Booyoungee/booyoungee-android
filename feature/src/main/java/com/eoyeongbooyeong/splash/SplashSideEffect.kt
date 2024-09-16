package com.eoyeongbooyeong.splash

sealed class SplashSideEffect {
    data object NavigateToHome : SplashSideEffect()
    data object NavigateToLogin : SplashSideEffect()
}