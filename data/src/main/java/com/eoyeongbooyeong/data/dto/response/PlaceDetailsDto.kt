package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class PlaceDetailsDto(
    @Serializable
    val placeId: String?,
    @Serializable
    val name: String?,
    @Serializable
    val address: String?,
    @Serializable
    val tel: String?,
    @Serializable
    val images: List<String>?,
    @Serializable
    val type: String?,
    @Serializable
    val movies: List<String>?,
    @Serializable
    val posterUrl: String?,
    @Serializable
    val likeCount: Int?,
    @Serializable
    val starCount: Int?,
)