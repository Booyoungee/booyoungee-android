package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.MoviePlaceEntity

interface MovieRepository {
    suspend fun searchOnKeyword(
        numOfRows: Int,
        pageNo: Int,
        keyword: String,
    ): Result<List<MoviePlaceEntity>>
}
