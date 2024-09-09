package com.eoyeongbooyeong.domain.entity

data class PlaceEntity(
    val name: String = "",
    val address: String = "",
    val star: Float = 0.0f,
    val reviewCount: Int = 0,
    val imageUrl: String = "",
    val likedCount: Int = 0,
    val movieNameList: List<String> = emptyList(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

data class PlaceInfoEntity(
    val placeId: String = "",
    val name: String = "",
    val address: String = "",
    val tel: String? = null,
    val images: List<String> = emptyList(),
    val type: String = "",
    val movies: List<String>? = null,
    val posterUrl: List<String>? = null,
    val likeCount: Int = 0,
    val starCount: Float = 0f,
    val reviewCount: Int = 0,
)
