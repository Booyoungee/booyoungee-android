package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.MyReviewEntity
import com.eoyeongbooyeong.domain.entity.ReviewInfoEntity

interface ReviewRepository {
    suspend fun getMyReviews(): Result<List<MyReviewEntity>>
    suspend fun getReviews(placeId: Int): Result<List<ReviewInfoEntity>>
    suspend fun writeReview(placeId: Int, content: String, stars: Int, type: String): Result<Int>
    suspend fun accuseReview(commentId: Int): Result<Int>
}
