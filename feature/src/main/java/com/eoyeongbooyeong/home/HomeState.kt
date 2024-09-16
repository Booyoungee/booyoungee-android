package com.eoyeongbooyeong.home

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity

data class HomeState(
    val recommendedPlace: List<PlaceInfoEntity> = emptyList(),
)
