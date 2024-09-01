package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.domain.model.TokenModel

interface AuthDataSource {
    suspend fun postReissueTokens(
        refreshToken: String
    ): TokenModel

    suspend fun postLogin(
        accessToken: String
    ): TokenModel
}
