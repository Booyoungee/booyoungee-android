package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.NicknameDto
import com.eoyeongbooyeong.data.dto.response.TokenDto
import com.eoyeongbooyeong.data.dto.response.UserIdDto

interface AuthDataSource {
    suspend fun postReissueTokens(
        refreshToken: String,
    ): BaseResponse<TokenDto>

    suspend fun postLogin(
        accessToken: String,
        refreshToken: String,
    ): BaseResponse<TokenDto>

    suspend fun deleteWithDraw(
        accessToken: String,
    ): BaseResponse<UserIdDto>

    suspend fun postLogout(
        accessToken: String,
    ): BaseResponse<UserIdDto>

    suspend fun postSignup(
        accessToken: String,
        refreshToken: String,
        nickname: NicknameDto,
    ): BaseResponse<TokenDto>
}
