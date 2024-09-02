package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.TokenEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
) {
    fun toEntity() = TokenEntity(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
