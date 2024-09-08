package com.eoyeongbooyeong.places.map

sealed class KakaoMapSideEffect {
    data class ShowToast(val message: String) : KakaoMapSideEffect()
    data object NavigateToPlaceDetailScreen : KakaoMapSideEffect()
}