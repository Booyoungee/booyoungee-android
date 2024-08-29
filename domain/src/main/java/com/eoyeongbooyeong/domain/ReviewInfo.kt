package com.eoyeongbooyeong.domain

data class ReviewInfo(
    val id: Int = -1,
    val placeId: Int = -1,
    val reviewContent: String = "",
    val reviewScore: Float = 0f,
    val writerId: Int = -1,
    val writerNickName: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
)
