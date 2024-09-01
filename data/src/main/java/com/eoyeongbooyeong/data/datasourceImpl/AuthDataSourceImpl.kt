package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.AuthDataSource
import com.eoyeongbooyeong.data.service.AuthService
import com.eoyeongbooyeong.domain.model.TokenModel
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthDataSource {
    override suspend fun postReissueTokens(refreshToken: String): TokenModel {
        return authService.postReissueTokens(refreshToken)
    }

    override suspend fun postLogin(accessToken: String): TokenModel {
        return authService.postLogin(accessToken)
    }
}
