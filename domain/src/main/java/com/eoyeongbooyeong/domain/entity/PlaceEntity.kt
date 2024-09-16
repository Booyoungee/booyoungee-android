package com.eoyeongbooyeong.domain.entity

data class PlaceInfoEntity(
    val placeId: String = "",
    val placeType: String = "",
    val name: String = "",
    val address: String = "",
    val tel: String? = null,
    val images: List<String> = emptyList(),
    val type: String = "",
    val movies: List<String>? = null,
    val posterUrl: List<String>? = null,
    val likeCount: Int = 0,
    val starCount: Int = 0,
    val stampCount: Int = 0,
    val reviewCount: Int = 0,
    val bookmarkCount: Int = 0,
    val stars: Double = 0.0,
    val mapX: String = "129.03933",
    val mapY: String = "35.114495",
    val me: MeEntity = MeEntity(),
)