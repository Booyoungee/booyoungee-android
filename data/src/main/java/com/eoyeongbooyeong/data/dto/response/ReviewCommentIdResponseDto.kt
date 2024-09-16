package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ReviewCommentIdResponseDto(
    @Serializable
    val commentId: Int
)