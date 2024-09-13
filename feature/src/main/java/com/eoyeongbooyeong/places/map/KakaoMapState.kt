package com.eoyeongbooyeong.places.map

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity

data class KakaoMapState(
    val placeList: List<PlaceInfoEntity> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
