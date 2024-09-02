package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.UserDataSource
import com.eoyeongbooyeong.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override suspend fun isAvailableNickname(nickname: String): Result<String> = runCatching {
        userDataSource.getIsAvailableNickname(nickname)
    }
}
