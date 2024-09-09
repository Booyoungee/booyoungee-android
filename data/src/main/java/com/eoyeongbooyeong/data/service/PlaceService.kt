package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.PlaceDto
import retrofit2.http.GET

interface PlaceService {
    @GET("api/v1/recommend")
    suspend fun getRecommendPlace(): BaseResponse<BaseContents<PlaceDto>>
}
