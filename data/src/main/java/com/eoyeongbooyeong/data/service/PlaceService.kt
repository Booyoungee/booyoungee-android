package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.NicknameDto
import com.eoyeongbooyeong.data.dto.response.PlaceDto
import com.eoyeongbooyeong.data.dto.response.UserDto
import com.eoyeongbooyeong.domain.entity.PlaceEntity
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface PlaceService {
    @GET("api/v1/recommend")
    suspend fun getRecommendPlace(): BaseResponse<BaseContents<PlaceDto>>
}
