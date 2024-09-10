package com.eoyeongbooyeong.mypage.review

sealed class MyReviewSideEffect {
    data object NavigateUp : MyReviewSideEffect()
}
