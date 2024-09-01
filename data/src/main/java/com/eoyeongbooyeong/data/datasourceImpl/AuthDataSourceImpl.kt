package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.AuthDataSource
import com.eoyeongbooyeong.domain.model.TokenModel
import com.eoyeongbooyeong.domain.repository.AuthRepository
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : AuthDataSource {
    override suspend fun reissueTokens(refreshToken: String): Result<TokenModel> {
        return authRepository.reissueTokens(refreshToken)
    }
}
