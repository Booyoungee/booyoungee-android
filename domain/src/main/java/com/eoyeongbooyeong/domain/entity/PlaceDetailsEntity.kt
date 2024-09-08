package com.eoyeongbooyeong.domain.entity

data class PlaceDetailsEntity(
    val placeId: String = "",
    val name: String = "",
    val address: String = "",
    val tel: String = "",
    val type: String = "",
    val starCount: Float = 0.0f,
    val reviewCount: Int = 0,
    val imageUrl: List<String> = emptyList(),
    val likeCount: Int = 0,
    val movieNameList: List<String> = emptyList(),
    val posterUrlList: List<String> = emptyList(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)
