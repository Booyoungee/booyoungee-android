package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.PlaceDataSource
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeDataSource: PlaceDataSource,
) : PlaceRepository {
    override suspend fun getRecommendPlace(): Result<List<PlaceInfoEntity>> = runCatching {
        placeDataSource.getRecommendPlace().data.contents.map { it.toDomain() }
    }
}
