package com.eoyeongbooyeong.places.details


data class PlaceDetailsState(
    val isLiked: Boolean = false,
    val isBookmarked: Boolean = false,
    val likeId: Int = 0,
    val bookMarkId: Int = 0
)