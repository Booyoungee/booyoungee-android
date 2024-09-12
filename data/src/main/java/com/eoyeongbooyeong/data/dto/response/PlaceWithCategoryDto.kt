package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.PlaceInfoWithCategoryEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceWithCategoryDto(
    @SerialName("id")
    val placeId: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("basicAddress")
    val address: String?,
    @SerialName("stars")
    val starCount: Float?,
    @SerialName("likes")
    val likeCount: Int?,
    @SerialName("reviews")
    val reviewCount: Int?,
    @SerialName("movieName")
    val movieName: String?,
) {
    fun toDomain() =
        PlaceInfoWithCategoryEntity(
            placeId = placeId ?: 0,
            name = name ?: "",
            basicAddress = address ?: "",
            likes = likeCount ?: 0,
            stars = starCount ?: 0.0f,
            movieName = movieName ?: "",
        )
}
