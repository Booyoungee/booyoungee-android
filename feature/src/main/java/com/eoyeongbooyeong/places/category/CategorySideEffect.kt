package com.eoyeongbooyeong.places.category

sealed class CategorySideEffect {
    data class ShowToast(
        val message: String,
    ) : CategorySideEffect()

    object NavigateToDetail : CategorySideEffect()
    object NavigateToBack : CategorySideEffect()
}
