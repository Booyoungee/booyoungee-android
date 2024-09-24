package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.StampDataSource
import com.eoyeongbooyeong.domain.entity.StampEntity
import com.eoyeongbooyeong.domain.repository.StampRepository
import javax.inject.Inject

class StampRepositoryImpl @Inject constructor(
    private val stampDataSource: StampDataSource,
) : StampRepository {
    override suspend fun getMyStampList(): Result<List<StampEntity>> = runCatching {
        stampDataSource.getMyStampList().map { it.toDomain() }
    }

    override suspend fun stampPlace(
        placeId: Int,
        type: String,
        userX: String,
        userY: String,
        x: String,
        y: String,
    ): Result<Int> = runCatching {
        stampDataSource.stampPlace(
            placeId,
            type,
            userX = userX,
            userY = userY,
            x,
            y
        )
    }

    override suspend fun getNearbyStampList(
        userX: String,
        userY: String,
        radius: Int,
    ): Result<List<StampEntity>> = runCatching {
        stampDataSource.getNearbyStampList(
            userX,
            userY,
            radius
        ).map { it.toDomain() }
    }
}
