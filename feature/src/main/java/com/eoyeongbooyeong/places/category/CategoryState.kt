package com.eoyeongbooyeong.places.category

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity

data class CategoryState(
    val filter: String = "star",
    val placeType: String = "",
    val placeList: List<PlaceInfoEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isBookmarked: Boolean = false,
)
