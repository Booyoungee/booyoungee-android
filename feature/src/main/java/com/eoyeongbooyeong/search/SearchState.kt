package com.eoyeongbooyeong.search

import com.eoyeongbooyeong.domain.entity.HotPlaceEntity
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SearchState(
    val hotTravelDestinationsFetchTime: String = "2024년 10월 01일 08:00 기준",
    val hotTravelDestinations: ImmutableList<HotPlaceEntity> = persistentListOf(),
    val searchResults: ImmutableList<PlaceDetailsEntity> = persistentListOf(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val totalCount: Int = 0,
    val isPagingLoading: Boolean = false,
)
