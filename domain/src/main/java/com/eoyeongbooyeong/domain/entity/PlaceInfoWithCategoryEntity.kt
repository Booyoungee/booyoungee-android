package com.eoyeongbooyeong.domain.entity

data class PlaceInfoWithCategoryEntity(
    val placeId: Int = -1,
    val name: String = "",
    val basicAddress: String = "",
    val stars: Float = 0.0f,
    val likes: Int = 0,
    val reviews: Int = 0,
    val movieName: String = "",
)
