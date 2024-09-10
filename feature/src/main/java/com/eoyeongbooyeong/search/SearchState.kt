package com.eoyeongbooyeong.search

import android.app.appsearch.SearchResult
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SearchState(
    val query: String = "",
    val hotTravelDestinationsFetchTime: String = "2024년 10월 01일 08:00 기준",
    val hotTravelDestinations: ImmutableList<String> = persistentListOf(),
    val searchResults: ImmutableList<SearchResult> = persistentListOf(),
    val isEmpty: Boolean = false,
)
