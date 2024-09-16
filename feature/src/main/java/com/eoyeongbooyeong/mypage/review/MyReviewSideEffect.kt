package com.eoyeongbooyeong.mypage.review

sealed class MyReviewSideEffect {
    data object NavigateUp : MyReviewSideEffect()
    data class NavigateToPlaceDetail(val placeId: Int, val type: String) : MyReviewSideEffect()
}
