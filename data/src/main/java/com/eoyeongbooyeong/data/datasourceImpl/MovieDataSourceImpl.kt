package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.MovieDataSource
import com.eoyeongbooyeong.data.dto.response.MovieDto
import com.eoyeongbooyeong.data.service.MovieService
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieService: MovieService,
) : MovieDataSource {
    override suspend fun searchOnKeyword(
        numOfRows: Int,
        pageNo: Int,
        keyword: String,
    ): MovieDto {
        return movieService.searchOnKeyword(numOfRows, pageNo, keyword).data
    }
}
