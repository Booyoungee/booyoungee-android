package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.TourInfoPlaceDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TourInfoOpenApiService {
    @GET("api/v1/tourInfoOpenApi/keyword")
    suspend fun searchOnKeyword(
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("keyword") keyword: String,
    ): BaseResponse<List<TourInfoPlaceDto>>
}
