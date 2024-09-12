package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.PlaceDataSource
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.data.dto.response.BookMarkDto
import com.eoyeongbooyeong.data.dto.response.LikeDto
import com.eoyeongbooyeong.data.dto.response.PlaceDetailsDto
import com.eoyeongbooyeong.domain.entity.BookMarkEntity
import com.eoyeongbooyeong.domain.entity.LikeEntity
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import com.eoyeongbooyeong.domain.entity.PlaceInfoWithCategoryEntity
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeDataSource: PlaceDataSource,
) : PlaceRepository {
    override suspend fun getRecommendPlace(): Result<List<PlaceInfoEntity>> = runCatching {
        placeDataSource.getRecommendPlace().data.contents.map { it.toDomain() }
    }
    override suspend fun getPlaceDetails(placeId: Int, placeType: String): Result<PlaceDetailsEntity> = runCatching {
        placeDataSource.getPlaceDetails(placeId, placeType).toPlaceDetailsEntity()
    }

    override suspend fun postBookMark(placeId: Int, placeType: String): Result<BookMarkEntity> = runCatching {
        placeDataSource.postBookMark(placeId, placeType).toBookMarkEntity()
    }

    override suspend fun deleteBookMark(bookMarkId: Int): Result<BookMarkEntity> = runCatching {
        placeDataSource.deleteBookMark(bookMarkId).toBookMarkEntity()
    }

    override suspend fun postLike(placeId: Int): Result<LikeEntity> = runCatching {
        placeDataSource.postLike(placeId).toLikeEntity()
    }

    override suspend fun deleteLike(likeId: Int): Result<LikeEntity> = runCatching {
        placeDataSource.deleteLike(likeId).toLikeEntity()
    }

    override suspend fun getMoviePlacesWithCategory(filter: String): Result<List<PlaceInfoWithCategoryEntity>> = runCatching {
        placeDataSource.getMoviePlacesWithCategory(filter).contents.map { it.toDomain() }
    }

    override suspend fun getLocalStorePlacesWithCategory(filter: String): Result<List<PlaceInfoWithCategoryEntity>> = runCatching {
        placeDataSource.getLocalStorePlacesWithCategory(filter).contents.map { it.toDomain() }
    }

    override suspend fun getTourPlacesWithCategory(filter: String): Result<List<PlaceInfoWithCategoryEntity>> = runCatching {
        placeDataSource.getTourPlacesWithCategory(filter).contents.map { it.toDomain() }
    }
}

private fun PlaceDetailsDto.toPlaceDetailsEntity(): PlaceDetailsEntity {
    return PlaceDetailsEntity(
        placeId = placeId ?: "",
        name = name ?: "",
        address = address ?: "",
        tel = tel ?: "",
        imageUrl = images ?: emptyList(),
        likeCount = likeCount ?: 0,
        starCount = starCount?.toFloat() ?: 0.0f,
        movieNameList = movies ?: emptyList(),
        posterUrlList = listOfNotNull(posterUrl),
        type = type ?: "",
    )
}

private fun BookMarkDto.toBookMarkEntity(): BookMarkEntity {
    return BookMarkEntity(
        bookmarkId = bookmarkId,
    )
}

private fun LikeDto.toLikeEntity(): LikeEntity {
    return LikeEntity(
        likeId = likeId,
    )
}
