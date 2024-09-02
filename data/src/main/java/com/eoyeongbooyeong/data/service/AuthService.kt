package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.NicknameDto
import com.eoyeongbooyeong.data.dto.response.TokenDto
import com.eoyeongbooyeong.data.dto.response.TokenWithNicknameDto
import com.eoyeongbooyeong.domain.entity.TokenEntity
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("token/reissue")
    suspend fun postReissueTokens(
        @Header("Authorization") refreshToken: String,
    ): TokenDto

    @POST("api/v1/oauth")
    suspend fun postLogin(
        @Header("X-Kakao-Access-Token") accessToken: String,
        @Header("X-Kakao-Refresh-Token") refreshToken: String,
    ): BaseResponse<TokenDto>

    @POST("api/v1/oauth/signup")
    suspend fun postSignup(
        @Header("X-Kakao-Access-Token") accessToken: String,
        @Header("X-Kakao-Refresh-Token") refreshToken: String,
        @Body nickname: NicknameDto,
    ): BaseResponse<TokenWithNicknameDto>
}
