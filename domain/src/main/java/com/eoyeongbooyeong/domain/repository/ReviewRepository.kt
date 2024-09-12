package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.ReviewInfoEntity

interface ReviewRepository {
    suspend fun getMyReviews(): Result<List<ReviewInfoEntity>>
    suspend fun getReviews(placeId: Int): Result<List<ReviewInfoEntity>>
}
