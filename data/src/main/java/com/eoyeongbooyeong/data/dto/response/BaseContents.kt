package com.eoyeongbooyeong.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseContents<T>(
    @SerialName("contents")
    val contents: List<T>,
)
