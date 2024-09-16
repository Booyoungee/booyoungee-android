package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.TourInfoOpenApiDataSource
import com.eoyeongbooyeong.domain.entity.TourInfoEntity
import com.eoyeongbooyeong.domain.repository.TourInfoRepository
import javax.inject.Inject

class TourInfoOpenApiRepositoryImpl @Inject constructor(
    private val tourInfoOpenApiDataSource: TourInfoOpenApiDataSource,
) : TourInfoRepository {
    override suspend fun searchOnKeyword(
        numOfRows: Int,
        pageNo: Int,
        keyword: String,
    ): Result<List<TourInfoEntity>> = runCatching {
        tourInfoOpenApiDataSource.searchOnKeyword(numOfRows, pageNo, keyword).data.map {
            it.toDomain()
        }
    }
}
