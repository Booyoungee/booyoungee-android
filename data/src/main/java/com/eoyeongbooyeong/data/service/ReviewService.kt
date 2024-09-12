package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.ReviewDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewService {
    @GET("api/v1/review/my")
    suspend fun getMyReviewList(): BaseResponse<BaseContents<ReviewDto>>


    @GET("api/v1/review/{placeId}")
    suspend fun getMyReviewList(
        @Path("placeId") placeId: Int,
    ): BaseResponse<BaseContents<ReviewDto>>

}
