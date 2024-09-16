package com.eoyeongbooyeong.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LikeRequestDto(
    @SerialName("placeId")
    val placeId: Int,
)