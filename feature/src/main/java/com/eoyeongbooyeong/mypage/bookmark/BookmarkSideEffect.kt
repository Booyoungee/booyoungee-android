package com.eoyeongbooyeong.mypage.bookmark

import com.eoyeongbooyeong.mypage.MyPageSideEffect

sealed class BookmarkSideEffect {
    data object NavigateUp : BookmarkSideEffect()
    data class NavigateToPlaceDetail(val placeId: Int, val type: String) : BookmarkSideEffect()
}
