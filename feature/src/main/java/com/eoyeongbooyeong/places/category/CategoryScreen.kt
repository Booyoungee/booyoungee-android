package com.eoyeongbooyeong.places.category

import SortingDropdownMenu
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.designsystem.component.LoadingWithProgressIndicator
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.core.extension.toast
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.domain.entity.PlaceType
import com.eoyeongbooyeong.feature.R
import com.eoyeongbooyeong.places.component.FloatingButtonContainer
import com.eoyeongbooyeong.search.component.PlaceInfoListItem
import com.google.common.collect.ImmutableList

@Composable
fun PlaceCategoryRoute(
    placeType: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    viewModel: CategoryPlaceViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        when (placeType) {
            "movie" -> viewModel.getMoviePlaceListWitFilter(state.value.filter)
            "store" -> viewModel.getLocalStorePlaceListWitFilter(state.value.filter)
            "tour" -> viewModel.getTourPlaceListWitFilter(state.value.filter)
        }
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects
            .flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is CategorySideEffect.ShowToast -> context.toast(sideEffect.message)

                    is CategorySideEffect.clickMovieTab -> {
                        viewModel.getMoviePlaceListWitFilter(state.value.filter)
                    }

                    is CategorySideEffect.clickLocalStoreTab -> {
                        viewModel.getLocalStorePlaceListWitFilter(state.value.filter)
                    }

                    is CategorySideEffect.clickTourTab -> {
                        viewModel.getTourPlaceListWitFilter(state.value.filter)
                    }

                    is CategorySideEffect.NavigateToBack -> {
                    }

                    is CategorySideEffect.NavigateToDetail -> {
                    }
                }
            }
    }

    Log.d(
        "PlaceCategoryRoute",
        "${state.value.placeList}, ${state.value.filter}, ${state.value.placeType}",
    )

    PlaceCategoryScreen(
        placeType = placeType,
        modifier = modifier,
        onBackClick = onBackClick,
        placeList = state.value.placeList,
        onSortingSelected = { selectedFilter ->
            viewModel.updateState(state.value.copy(filter = selectedFilter))
            when (placeType) {
                "movie" -> viewModel.getMoviePlaceListWitFilter(selectedFilter)
                "store" -> viewModel.getLocalStorePlaceListWitFilter(selectedFilter)
                "tour" -> viewModel.getTourPlaceListWitFilter(selectedFilter)
            }
        },
        navigateToMap = {},
        isLoading = state.value.isLoading,
    )
}

@Composable
fun PlaceCategoryScreen(
    modifier: Modifier,
    placeList: List<PlaceInfoEntity>,
    onBackClick: () -> Unit,
    onSortingSelected: (String) -> Unit,
    navigateToMap: () -> Unit,
    placeType: String,
    isLoading: Boolean = false,
    viewModel: CategoryPlaceViewModel = hiltViewModel(),
) {
    val selectedIndex =
        remember {
            mutableStateOf(
                when (placeType) {
                    "movie" -> 0
                    "store" -> 1
                    "tour" -> 2
                    else -> 0
                },
            )
        }
    val tabItemTitle =
        listOf(
            stringResource(R.string.movie),
            stringResource(R.string.local),
            stringResource(R.string.tour),
        )

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(White)
                .statusBarsPadding()
                .systemBarsPadding(),
    ) {
        BooTextTopAppBar(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                    contentDescription = "left",
                    modifier = Modifier.noRippleClickable { onBackClick() },
                )
            },
            text = "",
        )

        TabRow(
            selectedTabIndex = selectedIndex.value,
            containerColor = Transparent,
            contentColor = Black,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex.value]),
                    color = Purple,
                )
            },
        ) {
            tabItemTitle.forEachIndexed { index, _ ->
                Tab(
                    selected = selectedIndex.value == index,
                    onClick = {
                        selectedIndex.value = index
                        when (index) {
                            0 -> viewModel.sendSideEffect(CategorySideEffect.clickMovieTab)
                            1 -> viewModel.sendSideEffect(CategorySideEffect.clickLocalStoreTab)
                            2 -> viewModel.sendSideEffect(CategorySideEffect.clickTourTab)
                        }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ) {
                                    if (selectedIndex.value != index) {
                                        selectedIndex.value = index
                                        when (index) {
                                            0 -> viewModel.sendSideEffect(CategorySideEffect.clickMovieTab)
                                            1 -> viewModel.sendSideEffect(CategorySideEffect.clickLocalStoreTab)
                                            2 -> viewModel.sendSideEffect(CategorySideEffect.clickTourTab)
                                        }
                                    }
                                }.padding(top = 8.dp, start = 24.dp, end = 24.dp, bottom = 6.dp),
                    ) {
                        Text(
                            text = tabItemTitle[index],
                            style = BooTheme.typography.caption1,
                            modifier = Modifier.padding(vertical = 14.dp),
                        )
                    }
                }
            }
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            SortingDropdownMenu(
                onSortSelected = onSortingSelected,
            )
        }

        PlaceList(
            searchResultList = placeList,
        )
        Spacer(modifier = Modifier.height(12.dp))
        // TODO 플로팅 버튼 Z 축 위로 올리기
        FloatingButtonContainer(
            onClick = { navigateToMap() },
        )
    }

    // API 호출 후 loading 상태인 경우
    if (isLoading) {
        LoadingWithProgressIndicator()
    }
}

@Composable
fun PlaceList(
    modifier: Modifier = Modifier,
    searchResultList: List<PlaceInfoEntity>,
    onPlaceClick: (PlaceInfoEntity) -> Unit = {},
) {
    LazyColumn {
        items(searchResultList) { place ->
            PlaceInfoListItem(
                placeName = place.name,
                address = place.address,
                star = place.stars,
                reviewCount = place.reviewCount,
                likedCount = place.likeCount,
                movieNameList = place.movies ?: ImmutableList.of(),
                placeImageUrl = place.images.firstOrNull(),
                onClick = { /* Handle item click */ },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview
fun CategoryScreenPreview() {
    BooTheme {
        PlaceCategoryRoute(
            placeType = PlaceType.MOVIE.name.lowercase(),
        )
    }
}
