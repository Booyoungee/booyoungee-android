package com.eoyeongbooyeong.domain.entity

data class MoviePlaceEntity(
    val id: Int,
    val name: String,
    val basicAddress: String,
    val mapX: String,
    val mapY: String,
    val stars: Float,
    val likes: Int,
    val reviews: Int,
    val movieName: String,
    val type: String,
    val images: List<String>
)
