package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.PlaceDataSource
import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.HotPlaceDto
import com.eoyeongbooyeong.data.service.PlaceService
import javax.inject.Inject

class PlaceDataSourceImpl @Inject constructor(
    private val placeService: PlaceService,
) : PlaceDataSource {
    override suspend fun getRecommendPlace() = placeService.getRecommendPlace()
    override suspend fun getHotPlace(): BaseResponse<BaseContents<HotPlaceDto>> = placeService.getHotPlace()
}
