package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.BookMarkEntity
import com.eoyeongbooyeong.domain.entity.LikeEntity
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity

interface PlaceRepository {
    suspend fun getPlaceDetails(placeId: Int, placeType: String): Result<PlaceDetailsEntity>
    suspend fun postBookMark(placeId: Int, placeType: String): Result<BookMarkEntity>
    suspend fun deleteBookMark(bookMarkId: Int): Result<BookMarkEntity>
    suspend fun postLike(placeId: Int): Result<LikeEntity>
    suspend fun deleteLike(likeId: Int): Result<LikeEntity>
}