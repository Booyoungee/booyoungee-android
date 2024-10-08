package com.eoyeongbooyeong.home

sealed class HomeSideEffect {
    data class NavigateToWebView(val url: String) : HomeSideEffect()
    data object NavigateToSearch : HomeSideEffect()
    data class NavigateToCategoryPlace(val placeType: String) : HomeSideEffect()
    data class NavigateToPlaceDetail(val placeId: Int, val placeName: String) : HomeSideEffect()
}
