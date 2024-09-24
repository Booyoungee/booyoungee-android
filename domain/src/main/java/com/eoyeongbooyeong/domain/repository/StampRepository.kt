package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.StampEntity

interface StampRepository {
    suspend fun getMyStampList(): Result<List<StampEntity>>

    suspend fun stampPlace(
        placeId: Int,
        type: String,
        userX: String,
        userY: String,
        x: String,
        y: String,
    ): Result<Int>

    suspend fun getNearbyStampList(
        userX: String,
        userY: String,
        radius: Int,
    ): Result<List<StampEntity>>
}