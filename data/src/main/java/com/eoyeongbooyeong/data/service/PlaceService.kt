package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.BookMarkDto
import com.eoyeongbooyeong.data.dto.response.LikeDto
import com.eoyeongbooyeong.data.dto.response.PlaceDetailsDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import com.eoyeongbooyeong.data.dto.response.PlaceDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceService {
    @GET("api/v1/recommend")
    suspend fun getRecommendPlace(): BaseResponse<BaseContents<PlaceDto>>

    @GET("api/v1/place/details")
    suspend fun getPlaceDetails(
        @Query("placeId") placeId: Int,
        @Query("placeType") placeType: String,
    ): BaseResponse<PlaceDetailsDto>

    @POST("api/v1/bookmark")
    suspend fun postBookMark(
        @Query("placeId") placeId: Int,
        @Query("type") placeType: String,
    ): BaseResponse<BookMarkDto>

    @DELETE("api/v1/bookmark")
    suspend fun deleteBookMark(
        @Query("bookMarkId") bookMarkId: Int,
    ): BaseResponse<BookMarkDto>

    @POST("api/v1/like")
    suspend fun postLike(
        @Body placeId: Int,
    ): BaseResponse<LikeDto>

    @DELETE("api/v1/like/{likeId}")
    suspend fun deleteLike(
        @Path("likeId") likeId: Int,
    ): BaseResponse<LikeDto>
}
