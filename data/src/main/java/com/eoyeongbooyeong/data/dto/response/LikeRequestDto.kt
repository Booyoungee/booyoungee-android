package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LikeRequestDto(
    @SerialName("placeId")
    val placeId: Int,
)