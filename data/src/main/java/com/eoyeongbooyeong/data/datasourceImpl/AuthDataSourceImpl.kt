package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.AuthDataSource
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.NicknameDto
import com.eoyeongbooyeong.data.dto.response.TokenDto
import com.eoyeongbooyeong.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthDataSource {
    override suspend fun postReissueTokens(refreshToken: String): TokenDto =
        authService.postReissueTokens(refreshToken)

    override suspend fun postLogin(
        accessToken: String,
        refreshToken: String,
    ): BaseResponse<TokenDto> = authService.postLogin(
        accessToken = accessToken,
        refreshToken = refreshToken
    )

    override suspend fun postSignup(
        accessToken: String,
        refreshToken: String,
        nickname: NicknameDto,
    ): BaseResponse<TokenDto> = authService.postSignup(
        accessToken = accessToken,
        refreshToken = refreshToken,
        nickname = nickname
    )
}
