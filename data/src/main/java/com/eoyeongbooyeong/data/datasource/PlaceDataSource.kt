package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.HotPlaceDto
import com.eoyeongbooyeong.data.dto.response.PlaceDto

interface PlaceDataSource {
    suspend fun getRecommendPlace(): BaseResponse<BaseContents<PlaceDto>>
    suspend fun getHotPlace(): BaseResponse<BaseContents<HotPlaceDto>>
}
