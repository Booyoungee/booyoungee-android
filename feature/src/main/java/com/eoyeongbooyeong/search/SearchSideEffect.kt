package com.eoyeongbooyeong.search

sealed class SearchSideEffect {
    data object NavigateUp : SearchSideEffect()
    data class NavigateToPlaceDetail(val placeId: Int, val type: String) : SearchSideEffect()
}
