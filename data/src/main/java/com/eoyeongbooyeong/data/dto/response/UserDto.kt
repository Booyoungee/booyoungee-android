package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("userId")
    val userId: Int,
)
