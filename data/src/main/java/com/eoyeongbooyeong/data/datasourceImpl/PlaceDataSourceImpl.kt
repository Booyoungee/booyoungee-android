package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.PlaceDataSource
import com.eoyeongbooyeong.data.dto.response.BookMarkDto
import com.eoyeongbooyeong.data.dto.response.LikeDto
import com.eoyeongbooyeong.data.dto.response.PlaceDetailsDto
import com.eoyeongbooyeong.data.service.PlaceService
import javax.inject.Inject

class PlaceDataSourceImpl
    @Inject
    constructor(
        private val placeService: PlaceService,
    ) : PlaceDataSource {
        override suspend fun getPlaceDetails(
            placeId: Int,
            placeType: String,
        ): PlaceDetailsDto = placeService.getPlaceDetails(placeId, placeType).data

        override suspend fun postBookMark(
            placeId: Int,
            placeType: String,
        ): BookMarkDto = placeService.postBookMark(placeId, placeType).data

        override suspend fun deleteBookMark(bookMarkId: Int): BookMarkDto = placeService.deleteBookMark(bookMarkId).data

        override suspend fun postLike(placeId: Int): LikeDto = placeService.postLike(placeId).data

        override suspend fun deleteLike(likeId: Int): LikeDto = placeService.deleteLike(likeId).data
    }
