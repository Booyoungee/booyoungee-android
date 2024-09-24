package com.eoyeongbooyeong.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StampPlaceRequestDto(
    @SerialName("placeId")
    val placeId: Int,
    @SerialName("type")
    val type: String,
    @SerialName("userX")
    val userX: String,
    @SerialName("userY")
    val userY: String,
    @SerialName("x")
    val x: String,
    @SerialName("y")
    val y: String,
)
