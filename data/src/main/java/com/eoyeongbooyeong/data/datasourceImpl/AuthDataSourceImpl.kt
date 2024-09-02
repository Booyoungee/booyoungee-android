package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.AuthDataSource
import com.eoyeongbooyeong.data.dto.response.TokenDto
import com.eoyeongbooyeong.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthDataSource {
    override suspend fun postReissueTokens(refreshToken: String): TokenDto {
        return authService.postReissueTokens(refreshToken)
    }

    override suspend fun postLogin(
        accessToken: String,
        refreshToken: String,
    ): TokenDto {
        return authService.postLogin(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
