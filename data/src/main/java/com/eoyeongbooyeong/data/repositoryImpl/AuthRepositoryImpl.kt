package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.AuthDataSource
import com.eoyeongbooyeong.data.datastore.BooDataStore
import com.eoyeongbooyeong.domain.entity.TokenEntity
import com.eoyeongbooyeong.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val booDataStore: BooDataStore,
) : AuthRepository {
    override suspend fun reissueTokens(refreshToken: String): Result<TokenEntity> = runCatching {
        authDataSource.postReissueTokens(refreshToken).toEntity()
    }

    override suspend fun login(accessToken: String, refreshToken: String): Result<TokenEntity> =
        runCatching {
            authDataSource.postLogin(accessToken, refreshToken).data.toEntity()
        }

    override suspend fun signup(
        accessToken: String,
        refreshToken: String,
        nickname: String,
    ): Result<TokenEntity> = runCatching {
        authDataSource.postSignup(accessToken, refreshToken, nickname).data.toEntity()
    }

    override suspend fun isAlreadyLogin(): Boolean = booDataStore.isAlreadyLogin()
    override suspend fun setTokens(accessToken: String, refreshToken: String) =
        booDataStore.setTokens(accessToken, refreshToken)

    override suspend fun clearInfo() = booDataStore.clearInfo()
}
