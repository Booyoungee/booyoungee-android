package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.NicknameDto
import com.eoyeongbooyeong.data.dto.response.TokenDto
import com.eoyeongbooyeong.data.dto.response.UserIdDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("token/reissue")
    suspend fun postReissueTokens(
        @Header(HEADER) refreshToken: String,
    ): BaseResponse<TokenDto>

    @POST("api/v1/oauth")
    suspend fun postLogin(
        @Header("X-Kakao-Access-Token") accessToken: String,
        @Header("X-Kakao-Refresh-Token") refreshToken: String,
    ): BaseResponse<TokenDto>

    @DELETE("api/v1/oauth")
    suspend fun deleteWithDraw(
        @Header(HEADER) accessToken: String,
    ): BaseResponse<UserIdDto>


    @POST("api/v1/oauth/signup")
    suspend fun postSignup(
        @Header("X-Kakao-Access-Token") accessToken: String,
        @Header("X-Kakao-Refresh-Token") refreshToken: String,
        @Body nickname: NicknameDto,
    ): BaseResponse<TokenDto>

    companion object {
        const val HEADER = "Authorization"
    }
}
