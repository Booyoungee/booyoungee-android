package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceWithCategoryDto(
    @SerialName("placeId")
    val id: Int?,
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
)
