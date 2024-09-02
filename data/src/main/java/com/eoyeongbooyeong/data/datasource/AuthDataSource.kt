package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.NicknameDto
import com.eoyeongbooyeong.data.dto.response.TokenDto
import com.eoyeongbooyeong.data.dto.response.TokenWithNicknameDto

interface AuthDataSource {
    suspend fun postReissueTokens(
        refreshToken: String,
    ): TokenDto

    suspend fun postLogin(
        accessToken: String,
        refreshToken: String,
    ): BaseResponse<TokenDto>

    suspend fun postSignup(
        accessToken: String,
        refreshToken: String,
        nickname: NicknameDto,
    ): BaseResponse<TokenWithNicknameDto>
}
