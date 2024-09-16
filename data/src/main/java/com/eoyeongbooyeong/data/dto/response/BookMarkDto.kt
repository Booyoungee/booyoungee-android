package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookMarkDto(
    @SerialName("bookmarkId")
    val bookmarkId: Int,
)