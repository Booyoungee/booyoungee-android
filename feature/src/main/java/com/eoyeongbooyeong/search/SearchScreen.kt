package com.eoyeongbooyeong.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.eoyeongbooyeong.core.designsystem.component.LoadingWithProgressIndicator
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.addFocusCleaner
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.domain.entity.HotPlaceEntity
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import com.eoyeongbooyeong.search.screen.HotTravelDestinationsScreen
import com.eoyeongbooyeong.search.screen.NoResultScreen
import com.eoyeongbooyeong.search.screen.SearchResultScreen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun SearchRoute(
    navigateUp: () -> Unit,
    navigateToPlaceDetail: (Int, String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    SearchSideEffect.NavigateUp -> navigateUp()
                    is SearchSideEffect.NavigateToPlaceDetail -> navigateToPlaceDetail(
                        sideEffect.placeId,
                        sideEffect.type
                    )
                }
            }
    }

    SearchScreen(
        query = query,
        isLoading = state.isLoading,
        hotTravelDestinationsFetchTime = state.hotTravelDestinationsFetchTime,
        hotTravelDestinations = state.hotTravelDestinations,
        searchResults = searchResults,
        totalCounts = state.totalCount,
        isPagingLoading = state.isPagingLoading,
        clickHotPlace = viewModel::clickHotPlace,
        queryValueChanged = viewModel::queryValueChanged,
        navigateUp = viewModel::navigateUp,
        navigateToPlaceDetail = viewModel::navigateToPlaceDetail,
    )
}

@Composable
private fun SearchScreen(
    query: String = "",
    hotTravelDestinationsFetchTime: String = "2024년 10월 01일 08:00 기준",
    isLoading: Boolean = false,
    hotTravelDestinations: ImmutableList<HotPlaceEntity> = persistentListOf(),
    searchResults: LazyPagingItems<PlaceDetailsEntity>,
    totalCounts: Int = 0,
    isPagingLoading: Boolean = false,
    clickHotPlace: (String) -> Unit = {},
    queryValueChanged: (String) -> Unit = {},
    navigateUp: () -> Unit = {},
    navigateToPlaceDetail: (Int, String) -> Unit = { _, _ -> },
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .addFocusCleaner(focusManager)
            .background(White)
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        ) {
            Icon(
                painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                contentDescription = "back button",
                modifier =
                Modifier
                    .noRippleClickable(navigateUp)
                    .align(Alignment.CenterVertically),
            )
            Spacer(modifier = Modifier.width(6.dp))
            BooSearchTextField(
                text = query,
                onValueChange = queryValueChanged,
                isActive = true,
                modifier = Modifier.weight(1f),
            )
        }

        if (isLoading) {
            LoadingWithProgressIndicator()
        } else if (query.isEmpty()) {
            HotTravelDestinationsScreen(
                modifier = Modifier.fillMaxSize(),
                hotTravelDestinationsFetchTime = hotTravelDestinationsFetchTime,
                hotTravelDestinations = hotTravelDestinations,
                clickHotPlace = clickHotPlace,
            )
        } else if (searchResults.itemCount == 0) {
            NoResultScreen(
                query = query,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            SearchResultScreen(
                modifier = Modifier.fillMaxSize(),
                isLoading = isPagingLoading,
                resultCount = totalCounts,
                searchResultList = searchResults,
            ) { id, type ->
                navigateToPlaceDetail(id, type)
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    BooTheme {
        // SearchScreen()
    }
}
