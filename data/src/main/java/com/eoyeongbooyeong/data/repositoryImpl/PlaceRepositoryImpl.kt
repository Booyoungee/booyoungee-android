package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.PlaceDataSource
import com.eoyeongbooyeong.data.dto.response.BookMarkDto
import com.eoyeongbooyeong.data.dto.response.LikeDto
import com.eoyeongbooyeong.domain.entity.BookMarkEntity
import com.eoyeongbooyeong.domain.entity.HotPlaceEntity
import com.eoyeongbooyeong.domain.entity.LikeEntity
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeDataSource: PlaceDataSource,
) : PlaceRepository {
    override suspend fun getRecommendPlace(): Result<List<PlaceInfoEntity>> =
        runCatching {
            placeDataSource
                .getRecommendPlace()
                .data.contents
                .map { it.toDomain() }
        }

    override suspend fun getPlaceDetails(
        placeId: Int,
        placeType: String,
    ): Result<PlaceInfoEntity> =
        runCatching {
            placeDataSource.getPlaceDetails(placeId, placeType)
        }

    override suspend fun postBookMark(
        placeId: Int,
        placeType: String,
    ): Result<BookMarkEntity> =
        runCatching {
            placeDataSource.postBookMark(placeId, placeType).toBookMarkEntity()
        }

    override suspend fun deleteBookMark(placeId: Int): Result<BookMarkEntity> =
        runCatching {
            placeDataSource.deleteBookMark(placeId).toBookMarkEntity()
        }

    override suspend fun postLike(placeId: Int): Result<LikeEntity> =
        runCatching {
            placeDataSource.postLike(placeId).toLikeEntity()
        }

    override suspend fun deleteLike(likeId: Int): Result<LikeEntity> =
        runCatching {
            placeDataSource.deleteLike(likeId).toLikeEntity()
        }

    override suspend fun getMoviePlacesWithCategory(filter: String): Result<List<PlaceInfoEntity>> =
        runCatching {
            placeDataSource.getMoviePlacesWithCategory(filter).contents.map { it.toDomain() }
        }

    override suspend fun getLocalStorePlacesWithCategory(filter: String): Result<List<PlaceInfoEntity>> =
        runCatching {
            placeDataSource.getLocalStorePlacesWithCategory(filter).contents.map { it.toDomain() }
        }

    override suspend fun getTourPlacesWithCategory(filter: String): Result<List<PlaceInfoEntity>> =
        runCatching {
            placeDataSource.getTourPlacesWithCategory(filter).contents.map { it.toDomain() }
        }

    override suspend fun getHotPlace(): Result<List<HotPlaceEntity>> = runCatching {
        placeDataSource.getHotPlace().data.contents.map { it.toDomain() }
    }
}

private fun BookMarkDto.toBookMarkEntity(): BookMarkEntity =
    BookMarkEntity(
        bookmarkId = bookmarkId,
    )

private fun LikeDto.toLikeEntity(): LikeEntity =
    LikeEntity(
        likeId = likeId,
    )