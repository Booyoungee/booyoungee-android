package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.PlaceDataSource
import com.eoyeongbooyeong.data.dto.response.PlaceDetailsDto
import com.eoyeongbooyeong.data.service.PlaceService
import javax.inject.Inject

class PlaceDataSourceImpl @Inject constructor(
    private val placeService: PlaceService
): PlaceDataSource {
    override suspend fun getPlaceDetails(placeId: Int, placeType: String): PlaceDetailsDto{
        return placeService.getPlaceDetails(placeId, placeType).data
    }
}