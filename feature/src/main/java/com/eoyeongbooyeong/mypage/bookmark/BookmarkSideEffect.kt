package com.eoyeongbooyeong.mypage.bookmark

sealed class BookmarkSideEffect {
    data object NavigateUp : BookmarkSideEffect()
}
