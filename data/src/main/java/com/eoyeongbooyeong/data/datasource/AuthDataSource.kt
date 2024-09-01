package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.domain.model.TokenModel

interface AuthDataSource {
    suspend fun reissueTokens(
        refreshToken: String
    ): Result<TokenModel>
}
