package com.eoyeongbooyeong.places.details

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.domain.entity.ReviewInfoEntity


data class PlaceDetailsState(
    val placeInfoEntity: PlaceInfoEntity = PlaceInfoEntity(),
    var likeCount: Int = PlaceInfoEntity().likeCount,
    //val bookMarkCount: Int = PlaceInfoEntity(),
    val isLiked: Boolean = false,
    val isBookmarked: Boolean = false,
    val likeId: Int = 0,
    val bookMarkId: Int = 0,
    val reviewList: List<ReviewInfoEntity> = emptyList()
)