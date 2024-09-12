package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CreatedReviewResponseDto(
    @Serializable
    val commentId: Int
)