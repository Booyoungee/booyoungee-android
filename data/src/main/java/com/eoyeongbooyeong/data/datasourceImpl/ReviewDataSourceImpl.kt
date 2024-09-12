package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.ReviewDataSource
import com.eoyeongbooyeong.data.dto.response.ReviewDto
import com.eoyeongbooyeong.data.service.ReviewService
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor(
    private val reviewService: ReviewService,
) : ReviewDataSource {
    override suspend fun getMyReviews(): List<ReviewDto> = reviewService.getMyReviewList().data.contents
    override suspend fun getReviews(placeId: Int): List<ReviewDto> = reviewService.getMyReviewList(placeId).data.contents
}
