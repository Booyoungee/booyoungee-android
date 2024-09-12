package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.BookMarkDto
import com.eoyeongbooyeong.data.dto.response.LikeDto
import com.eoyeongbooyeong.data.dto.response.PlaceDetailsDto
import com.eoyeongbooyeong.data.dto.response.PlaceDto
import com.eoyeongbooyeong.data.dto.response.PlaceWithCategoryDto

interface PlaceDataSource {
    suspend fun getPlaceDetails(placeId: Int, placeType: String): PlaceDetailsDto
    suspend fun postBookMark(placeId: Int, placeType: String): BookMarkDto
    suspend fun deleteBookMark(bookMarkId: Int): BookMarkDto
    suspend fun postLike(placeId: Int): LikeDto
    suspend fun deleteLike(likeId: Int): LikeDto
    suspend fun getRecommendPlace(): BaseResponse<BaseContents<PlaceDto>>
    suspend fun getMoviePlacesWithCategory(filter: String): BaseContents<PlaceWithCategoryDto>
    suspend fun getLocalStorePlacesWithCategory(filter: String): BaseContents<PlaceWithCategoryDto>
    suspend fun getTourPlacesWithCategory(filter: String): BaseContents<PlaceWithCategoryDto>
}
