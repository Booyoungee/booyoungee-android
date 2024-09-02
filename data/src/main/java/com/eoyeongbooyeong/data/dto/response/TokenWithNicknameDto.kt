package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.TokenEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenWithNicknameDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
) {
    fun toEntity() = TokenEntity(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
