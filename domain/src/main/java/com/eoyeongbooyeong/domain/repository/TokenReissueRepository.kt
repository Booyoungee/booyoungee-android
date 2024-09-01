package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.model.TokenModel

interface TokenReissueRepository {
    suspend fun reissueTokens(
        refreshToken: String
    ): Result<TokenModel>
}
