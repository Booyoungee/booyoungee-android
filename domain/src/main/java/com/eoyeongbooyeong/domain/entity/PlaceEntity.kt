package com.eoyeongbooyeong.domain.entity

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
    val stampCount: Int = 0,
    val reviewCount: Int = 0,
    val me: MeEntity = MeEntity(),
)
