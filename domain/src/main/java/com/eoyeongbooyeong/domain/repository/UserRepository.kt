package com.eoyeongbooyeong.domain.repository

interface UserRepository {
    suspend fun isAvailableNickname(
        nickname: String,
    ): Result<String>
}
