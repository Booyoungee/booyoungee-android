package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.PlaceDataSource
import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.HotPlaceDto
import com.eoyeongbooyeong.data.dto.response.BookMarkDto
import com.eoyeongbooyeong.data.dto.response.LikeDto
import com.eoyeongbooyeong.data.dto.request.LikeRequestDto
import com.eoyeongbooyeong.data.service.PlaceService
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import javax.inject.Inject

class PlaceDataSourceImpl @Inject constructor(
    private val placeService: PlaceService,
) : PlaceDataSource {
    override suspend fun getHotPlace(): BaseResponse<BaseContents<HotPlaceDto>> = placeService.getHotPlace()

    override suspend fun getRecommendPlace() = placeService.getRecommendPlace()

    override suspend fun getPlaceDetails(
        placeId: Int,
        placeType: String,
    ): PlaceInfoEntity = placeService.getPlaceDetails(placeId, placeType).data.toDomain()

    override suspend fun postBookMark(
        placeId: Int,
        placeType: String,
    ): BookMarkDto = placeService.postBookMark(placeId, placeType).data

    override suspend fun deleteBookMark(placeId: Int): BookMarkDto = placeService.deleteBookMark(placeId).data

    override suspend fun postLike(placeId: Int): LikeDto = placeService.postLike(LikeRequestDto(placeId)).data

    override suspend fun deleteLike(likeId: Int): LikeDto = placeService.deleteLike(likeId).data

    override suspend fun getMoviePlacesWithCategory(filter: String) = placeService.getMoviePlacesWithCategory(filter).data

    override suspend fun getLocalStorePlacesWithCategory(filter: String) = placeService.getLocalStorePlacesWithCategory(filter).data

    override suspend fun getTourPlacesWithCategory(filter: String) = placeService.getTourPlacesWithCategory(filter).data
}
