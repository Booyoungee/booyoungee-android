package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.TokenEntity

interface AuthRepository {
    suspend fun reissueTokens(
        refreshToken: String,
    ): Result<TokenEntity>

    suspend fun login(
        accessToken: String,
        refreshToken: String,
    ): Result<TokenEntity>

    suspend fun withDraw(
        accessToken: String
    ): Result<Unit>

    suspend fun signup(
        accessToken: String,
        refreshToken: String,
        nickname: String,
    ): Result<TokenEntity>

    suspend fun isAlreadyLogin(): Boolean
    suspend fun getAccessToken(): String
    suspend fun setTokens(
        accessToken: String,
        refreshToken: String,
    )

    suspend fun clearInfo()
}
