package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.PlaceDetailsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceDetailsService {
    @GET("api/vq/place/details")
    suspend fun getPlaceDetails(
        @Query("placeId") placeId: Int,
        @Query("placeType") placeType: String,
    ): BaseResponse<PlaceDetailsDto>
}
