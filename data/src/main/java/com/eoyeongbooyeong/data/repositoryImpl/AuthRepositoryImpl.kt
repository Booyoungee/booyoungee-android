package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.service.AuthService
import com.eoyeongbooyeong.domain.model.TokenModel
import com.eoyeongbooyeong.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun reissueTokens(refreshToken: String): Result<TokenModel> = runCatching {
        authService.reissueTokens(refreshToken)
    }
}
