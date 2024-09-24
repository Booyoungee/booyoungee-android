package com.eoyeongbooyeong.domain.entity

data class StampEntity (
    val stampId: Int? = null,
    val placeId: Int = 0,
    val placeName: String = "",
    val type: String = "",
    val images: List<String> = emptyList(),
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val mapX: String? = null,
    val mapY: String? = null
)
