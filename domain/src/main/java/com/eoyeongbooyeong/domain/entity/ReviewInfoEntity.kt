package com.eoyeongbooyeong.domain.entity

data class ReviewInfoEntity(
    val id: Int = -1,
    val placeId: Int = -1,
    val reviewContent: String = "",
    val reviewScore: Double = 0.0,
    val writerId: Int = -1,
    val writerNickName: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
)
