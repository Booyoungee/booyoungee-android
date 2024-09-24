package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.request.StampPlaceRequestDto
import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.StampDto
import com.eoyeongbooyeong.data.dto.response.StampIdDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StampService {
    @GET("api/v1/stamp")
    suspend fun getMyStampList(): BaseResponse<BaseContents<StampDto>>

    @POST("api/v1/stamp")
    suspend fun stampPlace(
        @Body body: StampPlaceRequestDto,
    ): BaseResponse<StampIdDto>

    @GET("api/v1/stamp/nearby")
    suspend fun getNearbyStampList(
        @Query("userX") userX: String,
        @Query("userY") userY: String,
        @Query("radius") radius: Int,
    ): BaseResponse<BaseContents<StampDto>>
}
