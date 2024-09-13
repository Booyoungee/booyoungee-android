package com.eoyeongbooyeong.places.map

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity

sealed class KakaoMapSideEffect {
    data class ShowToast(val message: String) : KakaoMapSideEffect()
    data object NavigateToPlaceDetailScreen : KakaoMapSideEffect()
    data object ClickPlaceDetailBox : KakaoMapSideEffect()
    data object HidePlaceDetailBox : KakaoMapSideEffect()
}