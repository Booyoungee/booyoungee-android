package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.model.TokenModel

interface AuthRepository {
    suspend fun reissueTokens(
        refreshToken: String,
    ): Result<TokenModel>

    suspend fun isAlreadyLogin(): Boolean
    suspend fun setTokens(
        accessToken: String,
        refreshToken: String,
    )

    suspend fun clearInfo()
}
