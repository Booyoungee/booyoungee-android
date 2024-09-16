package com.eoyeongbooyeong.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WritingReviewRequestDto(
    @SerialName("placeId")
    val placeId: Int,
    @SerialName("content")
    val content: String,
    @SerialName("stars")
    val stars: Int,
    @SerialName("type")
    val type: String,
)