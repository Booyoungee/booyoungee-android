package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.MovieDto
import com.eoyeongbooyeong.data.dto.response.MoviePlaceDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("api/v1/place/movie/search")
    suspend fun searchOnKeyword(
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("keyword") keyword: String,
    ): BaseResponse<MovieDto>
}
