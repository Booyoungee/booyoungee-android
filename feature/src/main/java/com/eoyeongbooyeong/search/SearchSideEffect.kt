package com.eoyeongbooyeong.search

sealed class SearchSideEffect {
    data object NavigateUp : SearchSideEffect()
}
