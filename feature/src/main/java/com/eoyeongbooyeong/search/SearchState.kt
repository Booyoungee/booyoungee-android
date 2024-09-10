package com.eoyeongbooyeong.search

import android.app.appsearch.SearchResult
import com.eoyeongbooyeong.domain.entity.HotPlaceEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SearchState(
    val query: String = "",
    val hotTravelDestinationsFetchTime: String = "2024년 10월 01일 08:00 기준",
    val hotTravelDestinations: ImmutableList<HotPlaceEntity> = persistentListOf(),
    val searchResults: ImmutableList<SearchResult> = persistentListOf(),
)
