package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.ReviewDto

interface ReviewDataSource {
    suspend fun getMyReviews(): List<ReviewDto>
}
