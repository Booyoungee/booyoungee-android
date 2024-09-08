package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.PlaceDetailsDto

interface PlaceDataSource {
    suspend fun getPlaceDetails(placeId: Int, placeType: String): PlaceDetailsDto
}