package com.eoyeongbooyeong.domain.repository

interface UserRepository {
    suspend fun isAvailableNickname(
        nickname: String,
    ): Result<String>

    suspend fun getUserNickname(): Result<String>

    suspend fun setUserNickname(nickname: String): Result<Unit>

    suspend fun blockUser(blockUserId: Int): Result<Unit>
}
