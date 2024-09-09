package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.PlaceEntity
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDto(
    @SerialName("placeId")
    val placeId: String,
    @SerialName("name")
    val name: String,
    @SerialName("address")
    val address: String,
    @SerialName("tel")
    val tel: String?,
    @SerialName("images")
    val images: List<String>,
    @SerialName("type")
    val type: String,
    @SerialName("movies")
    val movies: List<String>?,
    @SerialName("posterUrl")
    val posterUrl: List<String>?,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("starCount")
    val starCount: Int,
) {
    fun toDomain() = PlaceInfoEntity(
        placeId = placeId,
        name = name,
        address = address,
        tel = tel,
        images = images,
        type = type,
        movies = movies,
        posterUrl = posterUrl,
        likeCount = likeCount,
        starCount = starCount.toFloat(),
    )
}
