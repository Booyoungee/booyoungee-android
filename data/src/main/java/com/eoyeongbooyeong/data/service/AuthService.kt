package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.domain.model.TokenModel
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("token/reissue")
    suspend fun postReissueTokens(
        @Header("Authorization") refreshToken: String,
    ): TokenModel

    @POST("api/v1/oauth")
    suspend fun postLogin(
        @Header("Authorization") accessToken: String,
    ): TokenModel
}
