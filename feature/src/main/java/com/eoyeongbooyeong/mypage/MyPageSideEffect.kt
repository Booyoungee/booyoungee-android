package com.eoyeongbooyeong.mypage

sealed class MyPageSideEffect {
    data object RestartApp : MyPageSideEffect()
    data class NavigateToWebView(val url: String) : MyPageSideEffect()
    data object NavigateToEditNickname : MyPageSideEffect()
    data object NavigateToBookmark : MyPageSideEffect()
    data object NavigateToMyReview : MyPageSideEffect()
}
