package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.service.TokenReissueService
import com.eoyeongbooyeong.domain.model.TokenModel
import com.eoyeongbooyeong.domain.repository.TokenReissueRepository
import javax.inject.Inject

class TokenReissueRepositoryImpl @Inject constructor(
    private val tokenReissueService: TokenReissueService,
) : TokenReissueRepository {
    override suspend fun reissueTokens(refreshToken: String): Result<TokenModel> = runCatching {
        tokenReissueService.reissueTokens(refreshToken)
    }
}
