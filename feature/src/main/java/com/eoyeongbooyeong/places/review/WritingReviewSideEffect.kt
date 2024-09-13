package com.eoyeongbooyeong.places.review

sealed class WritingReviewSideEffect {
    data class ShowToast(
        val message: String,
    ) : WritingReviewSideEffect()
    data object NavigateBack : WritingReviewSideEffect()
    data object NavigateReviewFinish : WritingReviewSideEffect()
    data class ReviewError(val message: String) : WritingReviewSideEffect()
}