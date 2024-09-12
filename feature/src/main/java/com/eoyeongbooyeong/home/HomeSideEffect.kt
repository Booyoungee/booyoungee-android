package com.eoyeongbooyeong.home

sealed class HomeSideEffect {
    data class NavigateToWebView(val url: String) : HomeSideEffect()
}
