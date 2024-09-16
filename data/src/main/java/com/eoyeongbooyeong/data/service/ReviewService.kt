package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.request.WritingReviewRequestDto
import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.MyReviewDto
import com.eoyeongbooyeong.data.dto.response.ReviewCommentIdResponseDto
import com.eoyeongbooyeong.data.dto.response.ReviewDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewService {
    @GET("api/v1/review/my")
    suspend fun getMyReviewList(): BaseResponse<BaseContents<MyReviewDto>>


    @GET("api/v1/review/{placeId}")
    suspend fun getMyReviewList(
        @Path("placeId") placeId: Int,
    ): BaseResponse<BaseContents<ReviewDto>>

    @POST("api/v1/review")
    suspend fun writeReview(
        @Body request: WritingReviewRequestDto,
    ): BaseResponse<ReviewCommentIdResponseDto>

    @POST("api/v1/review/{commentId}/accuse")
    suspend fun accuseReview(
        @Path("commentId") commentId: Int,
    ): BaseResponse<ReviewCommentIdResponseDto>

}
