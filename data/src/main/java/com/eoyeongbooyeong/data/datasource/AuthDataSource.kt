package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.TokenDto

interface AuthDataSource {
    suspend fun postReissueTokens(
        refreshToken: String,
    ): TokenDto

    suspend fun postLogin(
        accessToken: String,
        refreshToken: String,
    ): TokenDto
}
