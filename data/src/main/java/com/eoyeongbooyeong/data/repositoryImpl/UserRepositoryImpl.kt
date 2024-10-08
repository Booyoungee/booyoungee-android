package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.UserDataSource
import com.eoyeongbooyeong.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override suspend fun isAvailableNickname(nickname: String): Result<String> = runCatching {
        userDataSource.getIsAvailableNickname(nickname).nickname
    }

    override suspend fun getUserNickname(): Result<String> = runCatching {
        userDataSource.getUserNickname().nickname
    }

    override suspend fun setUserNickname(nickname: String): Result<Unit> = runCatching {
        userDataSource.putNewNickname(nickname)
    }

    override suspend fun blockUser(blockUserId: Int): Result<Unit> = runCatching {
        userDataSource.postBlockUser(blockUserId)
    }
}
