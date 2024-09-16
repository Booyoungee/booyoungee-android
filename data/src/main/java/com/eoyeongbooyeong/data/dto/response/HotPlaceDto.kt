package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotPlaceDto (
    @SerialName("placeId")
    val placeId: Int,
    @SerialName("type")
    val type: String,
    @SerialName("name")
    val name: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("viewCount")
    val viewCount: Int
) {
    fun toDomain() = com.eoyeongbooyeong.domain.entity.HotPlaceEntity(
        placeId = placeId,
        type = type,
        name = name,
        updatedAt = updatedAt,
        viewCount = viewCount
    )
}
