package com.eoyeongbooyeong.places.details

sealed class PlaceDetailsSideEffect {
    data class ShowToast(val message: String) : PlaceDetailsSideEffect()
    data object NavigateToWritingReview : PlaceDetailsSideEffect()
}