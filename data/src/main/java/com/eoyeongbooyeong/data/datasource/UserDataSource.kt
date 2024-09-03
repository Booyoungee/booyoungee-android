package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.NicknameDto
import com.eoyeongbooyeong.data.dto.response.UserDto

interface UserDataSource {
    suspend fun getIsAvailableNickname(
        nickname: String,
    ): NicknameDto

    suspend fun getUserNickname(): UserDto
}
