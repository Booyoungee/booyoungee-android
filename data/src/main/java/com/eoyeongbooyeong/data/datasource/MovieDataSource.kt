package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.MoviePlaceDto

interface MovieDataSource {
    suspend fun searchOnKeyword(numOfRows: Int, pageNo: Int, keyword: String): List<MoviePlaceDto>
}
