package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.UserDataSource
import com.eoyeongbooyeong.data.dto.response.NicknameDto
import com.eoyeongbooyeong.data.dto.response.UserDto
import com.eoyeongbooyeong.data.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService,
) : UserDataSource {
    override suspend fun getIsAvailableNickname(nickname: String): NicknameDto =
        userService.getIsAvailableNickname(nickname).data

    override suspend fun getUserNickname(): UserDto = userService.getUserInfo().data
    override suspend fun putNewNickname(nickname: String): NicknameDto =
        userService.putNewNickname(nickname).data

    override suspend fun postBlockUser(blockUserId: Int): Int = userService.postBlockUser(blockUserId)
}
