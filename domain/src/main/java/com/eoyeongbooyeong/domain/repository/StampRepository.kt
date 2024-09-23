package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.StampEntity

interface StampRepository {
    suspend fun getNearbyStampList(
        userX: String,
        userY: String,
        radius: Int
    ): Result<List<StampEntity>>
}