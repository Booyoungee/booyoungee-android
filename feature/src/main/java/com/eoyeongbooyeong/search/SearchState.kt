package com.eoyeongbooyeong.search

import com.eoyeongbooyeong.domain.entity.HotPlaceEntity
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SearchState(
    val hotTravelDestinationsFetchTime: String = "2024년 10월 01일 08:00 기준",
    val hotTravelDestinations: ImmutableList<HotPlaceEntity> = persistentListOf(),
    val searchResults: ImmutableList<PlaceInfoEntity> = persistentListOf(
        PlaceInfoEntity(


        ),
    ),
)
