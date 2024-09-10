package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.TourInfoOpenApiDataSource
import com.eoyeongbooyeong.data.service.TourInfoOpenApiService
import javax.inject.Inject

class TourInfoOpenApiDataSourceImpl @Inject constructor(
    private val tourInfoOpenApiService: TourInfoOpenApiService,
) : TourInfoOpenApiDataSource {
    override suspend fun searchOnKeyword(
        numOfRows: Int,
        pageNo: Int,
        keyword: String,
    ) = tourInfoOpenApiService.searchOnKeyword(numOfRows, pageNo, keyword)
}
