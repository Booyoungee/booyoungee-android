package com.eoyeongbooyeong.places.details

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity


data class PlaceDetailsState(
    val placeInfoEntity: PlaceInfoEntity = PlaceInfoEntity(),
    val isLiked: Boolean = false,
    val isBookmarked: Boolean = false,
    val likeId: Int = 0,
    val bookMarkId: Int = 0
)