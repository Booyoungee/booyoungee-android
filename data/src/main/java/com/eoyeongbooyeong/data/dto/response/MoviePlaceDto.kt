package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.MoviePlaceEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    @SerialName("pageable")
    val pageable: Pageable,
    @SerialName("contents")
    val contents: List<MoviePlaceDto>,
)

@Serializable
data class MoviePlaceDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("basicAddress")
    val basicAddress: String,
    @SerialName("mapX")
    val mapX: String,
    @SerialName("mapY")
    val mapY: String,
    @SerialName("stars")
    val stars: Float,
    @SerialName("likes")
    val likes: Int,
    @SerialName("reviews")
    val reviews: Int,
    @SerialName("movieName")
    val movieName: String,
    @SerialName("type")
    val type: String,
    @SerialName("images")
    val images: List<String>,
) {
    fun toDomain() = MoviePlaceEntity(
        id = id,
        name = name,
        basicAddress = basicAddress,
        mapX = mapX,
        mapY = mapY,
        stars = stars,
        likes = likes,
        reviews = reviews,
        movieName = movieName,
        type = type,
        images = images
    )
}
