package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.StampDto

interface StampDataSource {
    suspend fun getMyStampList(): List<StampDto>

    suspend fun getNearbyStampList(
        userX: String,
        userY: String,
        radius: Int,
    ): List<StampDto>
}
