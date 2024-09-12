package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.ReviewDataSource
import com.eoyeongbooyeong.domain.entity.ReviewInfoEntity
import com.eoyeongbooyeong.domain.repository.ReviewRepository
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewDataSource: ReviewDataSource,
) : ReviewRepository {
    override suspend fun getMyReviews(): Result<List<ReviewInfoEntity>> = runCatching {
        reviewDataSource.getMyReviews().map { it.toDomain() }
    }

    override suspend fun getReviews(placeId: Int): Result<List<ReviewInfoEntity>> = runCatching {
        reviewDataSource.getReviews(placeId).map { it.toDomain() }
    }
}
