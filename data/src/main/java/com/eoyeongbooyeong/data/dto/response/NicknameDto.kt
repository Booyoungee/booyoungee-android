package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NicknameDto(
    @SerialName("nickname")
    val nickname: String,
)
