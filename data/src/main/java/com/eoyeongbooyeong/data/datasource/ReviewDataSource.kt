package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.ReviewDto

interface ReviewDataSource {
    suspend fun getMyReviews(): List<ReviewDto>
    suspend fun getReviews(placeId: Int): List<ReviewDto>
    suspend fun writeReview(placeId: Int, content: String, stars: Int): Int
}
