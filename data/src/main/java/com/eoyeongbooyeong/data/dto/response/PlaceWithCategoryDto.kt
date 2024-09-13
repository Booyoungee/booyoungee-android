package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
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
    val starCount: Double?,
    @SerialName("likes")
    val likeCount: Int?,
    @SerialName("reviews")
    val reviewCount: Int?,
    @SerialName("movieName")
    val movieName: String?,
) {
    fun toDomain() =
        PlaceInfoEntity(
            placeId = placeId.toString(),
            name = name ?: "",
            address = address ?: "",
            likeCount = likeCount ?: 0,
            stars = starCount ?: 0.0,
            movies = listOf(movieName ?: "")
        )
}
