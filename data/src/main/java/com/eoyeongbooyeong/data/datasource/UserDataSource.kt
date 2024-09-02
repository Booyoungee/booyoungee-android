package com.eoyeongbooyeong.data.datasource

interface UserDataSource {
    suspend fun getIsAvailableNickname(
        nickname: String,
    ): String
}
