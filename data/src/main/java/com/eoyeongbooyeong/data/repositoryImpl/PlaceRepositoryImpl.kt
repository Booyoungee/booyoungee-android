package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.PlaceDataSource
import com.eoyeongbooyeong.data.dto.response.PlaceDetailsDto
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeDataSource: PlaceDataSource,
): PlaceRepository {
    override suspend fun getPlaceDetails(placeId: Int, placeType: String): Result<PlaceDetailsEntity> = runCatching {
        placeDataSource.getPlaceDetails(placeId, placeType).toPlaceDetailsEntity()
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