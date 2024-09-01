package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.domain.model.TokenModel
import retrofit2.http.POST

interface TokenReissueService {
    @POST("token/reissue")
    suspend fun reissueTokens(
        refreshToken: String,
    ): TokenModel
}
