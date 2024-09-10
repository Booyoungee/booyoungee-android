package com.eoyeongbooyeong.search

import android.app.appsearch.SearchResult

data class SearchState(
    val query: String = "",
    val searchResults: List<SearchResult> = emptyList(),
    val isLoading: Boolean = false
)
