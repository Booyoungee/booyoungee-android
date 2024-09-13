package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.ReviewInfoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    @SerialName("id")
    val id: Int,
    @SerialName("placeId")
    val placeId: Int,
    @SerialName("content")
    val content: String,
    @SerialName("stars")
    val stars: Double,
    @SerialName("writerId")
    val writerId: Int,
    @SerialName("writerName")
    val writerName: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String,
) {
    fun toDomain() = ReviewInfoEntity(
        id = id,
        placeId = placeId,
        reviewContent = content,
        reviewScore = stars,
        writerId = writerId,
        writerNickName = writerName,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
