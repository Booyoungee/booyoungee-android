package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StampIdDto(
    @SerialName("stampId")
    val stampId: Int,
)
