package com.eoyeongbooyeong.new_home

sealed class NewHomeSideEffect {
    data class NavigateToWebView(val url: String) : NewHomeSideEffect()
}
