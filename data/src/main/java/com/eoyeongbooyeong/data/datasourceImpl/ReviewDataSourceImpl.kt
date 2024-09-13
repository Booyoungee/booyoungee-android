package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.ReviewDataSource
import com.eoyeongbooyeong.data.dto.request.WritingReviewRequestDto
import com.eoyeongbooyeong.data.dto.response.MyReviewDto
import com.eoyeongbooyeong.data.dto.response.ReviewDto
import com.eoyeongbooyeong.data.service.ReviewService
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor(
    private val reviewService: ReviewService,
) : ReviewDataSource {
    override suspend fun getMyReviews(): List<MyReviewDto> = reviewService.getMyReviewList().data.contents
    override suspend fun getReviews(placeId: Int): List<ReviewDto> = reviewService.getMyReviewList(placeId).data.contents
    override suspend fun writeReview(placeId: Int, content: String, stars: Int): Int = reviewService.writeReview(WritingReviewRequestDto(placeId, content, stars)).data.commentId
    override suspend fun accuseReview(commentId: Int): Int = reviewService.accuseReview(commentId).data.commentId
}
