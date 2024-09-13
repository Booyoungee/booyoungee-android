package com.eoyeongbooyeong.places.map

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity

data class KakaoMapState(
    val placeList: List<PlaceInfoEntity>? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val showDetailBox: Boolean = false,
    val selectedPlace: PlaceInfoEntity? = null,
)
