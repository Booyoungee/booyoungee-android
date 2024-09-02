package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.NicknameDto

interface UserDataSource {
    suspend fun getIsAvailableNickname(
        nickname: String,
    ): NicknameDto
}
