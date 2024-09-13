package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.NicknameDto
import com.eoyeongbooyeong.data.dto.response.UserDto
import com.eoyeongbooyeong.data.dto.response.UserIdDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("api/v1/user/nickname")
    suspend fun getIsAvailableNickname(
        @Query("nickname") nickname: String,
    ): BaseResponse<NicknameDto>

    @GET("api/v1/user/me")
    suspend fun getUserInfo(): BaseResponse<UserDto>

    @PUT("api/v1/user/nickname")
    suspend fun putNewNickname(
        @Query("nickname") nickname: String,
    ): BaseResponse<NicknameDto>

    @POST("api/v1/block/{blockUserId}")
    suspend fun postBlockUser(
        @Path("blockUserId") blockUserId: Int,
    ): BaseResponse<UserIdDto>
}
