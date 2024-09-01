package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.AuthDataSource
import com.eoyeongbooyeong.data.datastore.BooDataStore
import com.eoyeongbooyeong.domain.model.TokenModel
import com.eoyeongbooyeong.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val booDataStore: BooDataStore
) : AuthRepository {
    override suspend fun reissueTokens(refreshToken: String): Result<TokenModel> = runCatching {
        authDataSource.postReissueTokens(refreshToken)
    }

    override suspend fun login(accessToken: String): Result<TokenModel> = runCatching {
        authDataSource.postLogin(accessToken)
    }

    override suspend fun isAlreadyLogin(): Boolean = booDataStore.isAlreadyLogin()
    override suspend fun setTokens(accessToken: String, refreshToken: String) = booDataStore.setTokens(accessToken, refreshToken)
    override suspend fun clearInfo() = booDataStore.clearInfo()
}
