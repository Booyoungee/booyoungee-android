package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.HotPlaceDto
import com.eoyeongbooyeong.data.dto.response.BookMarkDto
import com.eoyeongbooyeong.data.dto.response.LikeDto
import com.eoyeongbooyeong.data.dto.response.PlaceDto
import com.eoyeongbooyeong.data.dto.response.PlaceWithCategoryDto
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity

interface PlaceDataSource {
    suspend fun getPlaceDetails(
        placeId: Int,
        placeType: String,
    ): PlaceInfoEntity

    suspend fun postBookMark(
        placeId: Int,
        placeType: String,
    ): BookMarkDto

    suspend fun deleteBookMark(placeId: Int): BookMarkDto

    suspend fun postLike(placeId: Int): LikeDto

    suspend fun deleteLike(likeId: Int): LikeDto

    suspend fun getRecommendPlace(): BaseResponse<BaseContents<PlaceDto>>

    suspend fun getHotPlace(): BaseResponse<BaseContents<HotPlaceDto>>

    suspend fun getMoviePlacesWithCategory(filter: String): BaseContents<PlaceWithCategoryDto>

    suspend fun getLocalStorePlacesWithCategory(filter: String): BaseContents<PlaceWithCategoryDto>

    suspend fun getTourPlacesWithCategory(filter: String): BaseContents<PlaceWithCategoryDto>
}
