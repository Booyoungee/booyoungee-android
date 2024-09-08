package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity

interface PlaceRepository {
    suspend fun getPlaceDetails(placeId: Int, placeType: String): Result<PlaceDetailsEntity>
}