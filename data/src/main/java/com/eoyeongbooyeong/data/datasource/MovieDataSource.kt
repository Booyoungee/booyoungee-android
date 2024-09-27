package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.MovieDto

interface MovieDataSource {
    suspend fun searchOnKeyword(numOfRows: Int, pageNo: Int, keyword: String): MovieDto
}
