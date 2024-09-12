package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.BookMarkEntity
import com.eoyeongbooyeong.domain.entity.LikeEntity
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.domain.entity.PlaceInfoWithCategoryEntity

interface PlaceRepository {
    suspend fun getRecommendPlace(): Result<List<PlaceInfoEntity>>
    suspend fun getPlaceDetails(placeId: Int, placeType: String): Result<PlaceDetailsEntity>
    suspend fun postBookMark(placeId: Int, placeType: String): Result<BookMarkEntity>
    suspend fun deleteBookMark(bookMarkId: Int): Result<BookMarkEntity>
    suspend fun postLike(placeId: Int): Result<LikeEntity>
    suspend fun deleteLike(likeId: Int): Result<LikeEntity>
    suspend fun getMoviePlacesWithCategory(filter: String): Result<List<PlaceInfoWithCategoryEntity>>
    suspend fun getLocalStorePlacesWithCategory(filter: String): Result<List<PlaceInfoWithCategoryEntity>>
    suspend fun getTourPlacesWithCategory(filter: String): Result<List<PlaceInfoWithCategoryEntity>>
}
