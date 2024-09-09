package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity

interface PlaceRepository {
    suspend fun getRecommendPlace(): Result<List<PlaceInfoEntity>>
}
