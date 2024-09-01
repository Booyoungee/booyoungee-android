package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datastore.BooDataStore
import com.eoyeongbooyeong.data.service.AuthService
import com.eoyeongbooyeong.domain.model.TokenModel
import com.eoyeongbooyeong.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val booDataStore: BooDataStore
) : AuthRepository {
    override suspend fun reissueTokens(refreshToken: String): Result<TokenModel> = runCatching {
        authService.reissueTokens(refreshToken)
    }

    override suspend fun isAlreadyLogin(): Boolean = booDataStore.isAlreadyLogin()
    override suspend fun setTokens(accessToken: String, refreshToken: String) = booDataStore.setTokens(accessToken, refreshToken)
}
