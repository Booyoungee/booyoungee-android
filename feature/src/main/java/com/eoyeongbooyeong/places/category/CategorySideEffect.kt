package com.eoyeongbooyeong.places.category

sealed class CategorySideEffect {
    data class ShowToast(
        val message: String,
    ) : CategorySideEffect()

    object NavigateToDetail : CategorySideEffect()
    object NavigateToBack : CategorySideEffect()
    object clickMovieTab : CategorySideEffect()
    object clickLocalStoreTab : CategorySideEffect()
    object clickTourTab : CategorySideEffect()
}
