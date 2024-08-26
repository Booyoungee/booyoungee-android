package com.eoyeongbooyeong.domain

data class Place(
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
