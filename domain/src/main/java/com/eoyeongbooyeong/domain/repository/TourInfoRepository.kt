package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.TourInfoEntity

interface TourInfoRepository {
    suspend fun searchOnKeyword(
        numOfRows: Int,
        pageNo: Int,
        keyword: String,
    ): Result<List<TourInfoEntity>>
}
