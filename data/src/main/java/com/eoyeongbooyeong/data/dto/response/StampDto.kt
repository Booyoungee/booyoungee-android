package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.StampEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StampDto (
    @SerialName("stampId")
    val stampId: Int?,
    @SerialName("placeId")
    val placeId: Int,
    @SerialName("placeName")
    val placeName: String,
    @SerialName("type")
    val type: String,
    @SerialName("images")
    val images: List<String>,
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("updatedAt")
    val updatedAt: String?,
    @SerialName("mapX")
    val mapX: String?,
    @SerialName("mapY")
    val mapY: String?
) {
    fun toDomain() = StampEntity(
        stampId = stampId,
        placeId = placeId,
        placeName = placeName,
        type = type,
        images = images,
        createdAt = createdAt,
        updatedAt = updatedAt,
        mapX = mapX,
        mapY = mapY
    )
}
