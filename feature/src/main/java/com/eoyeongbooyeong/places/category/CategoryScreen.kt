package com.eoyeongbooyeong.places.category

import SortingDropdownMenu
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.places.component.FloatingButtonContainer
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import com.eoyeongbooyeong.domain.entity.PlaceType
import com.eoyeongbooyeong.feature.R
import com.eoyeongbooyeong.search.component.PlaceInfoListItem
import com.google.common.collect.ImmutableList

@Composable
fun PlaceCategoryRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    searchResultList: ImmutableList<PlaceDetailsEntity> = ImmutableList.of(),
    placeType: String = PlaceType.MOVIE.name,
) {
    PlaceCategoryScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        searchResultList = searchResultList,
        onSortSelected = {},
        navigateToMap = {},
        placeType = placeType,
    )
}

@Composable
fun PlaceCategoryScreen(
    modifier: Modifier,
    searchResultList: List<PlaceDetailsEntity>,
    onBackClick: () -> Unit,
    onSortSelected: (String) -> Unit,
    navigateToMap: () -> Unit,
    placeType: String,
) {
    val selectedIndex =
        remember {
            mutableStateOf(
                when (placeType) {
                    PlaceType.MOVIE.name -> 0
                    PlaceType.STORE.name -> 1
                    PlaceType.TOUR.name -> 2
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
                    onClick = { selectedIndex.value = index },
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ) { selectedIndex.value = index }
                                .padding(top = 8.dp, start = 24.dp, end = 24.dp, bottom = 6.dp),
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
                onSortSelected = onSortSelected,
            )
        }

        PlaceList(
            searchResultList = searchResultList,
        )
        Spacer(modifier = Modifier.height(12.dp))
        // TODO 플로팅 버튼 Z 축 위로 올리기
        FloatingButtonContainer(
            onClick = { navigateToMap() },
        )
    }
}

@Composable
fun PlaceList(
    modifier: Modifier = Modifier,
    searchResultList: List<PlaceDetailsEntity>,
    onPlaceClick: (PlaceDetailsEntity) -> Unit = {},
) {
    LazyColumn {
        items(searchResultList) { place ->
            PlaceInfoListItem(
                placeName = place.name,
                address = place.address,
                star = place.starCount,
                reviewCount = place.reviewCount,
                likedCount = place.likeCount,
                movieNameList = place.movieNameList,
                placeImageUrl = place.imageUrl[0], // TODO : 슬라이드가 가능한 이미지 페이지로 변경해야 함
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
            searchResultList =
                ImmutableList.of(
                    PlaceDetailsEntity(
                        name = "피자헛",
                        address = "서울특별시 강남구 역삼동 123-456",
                        starCount = 4.5f,
                        reviewCount = 123,
                        likeCount = 456,
                        movieNameList = listOf("마블", "DC"),
                    ),
                    PlaceDetailsEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        starCount = 4.5f,
                        reviewCount = 123,
                        likeCount = 456,
                        movieNameList = listOf("마블", "DC"),
                    ),
                    PlaceDetailsEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        starCount = 4.5f,
                        reviewCount = 123,
                        likeCount = 456,
                        movieNameList = listOf("마블", "DC"),
                    ),
                    PlaceDetailsEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        starCount = 4.5f,
                        reviewCount = 123,
                        likeCount = 456,
                        movieNameList = listOf("마블", "DC"),
                    ),
                    PlaceDetailsEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        starCount = 4.5f,
                        reviewCount = 123,
                        likeCount = 456,
                        movieNameList = listOf("마블", "DC"),
                    ),
                    PlaceDetailsEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        starCount = 4.5f,
                        reviewCount = 123,
                        likeCount = 456,
                        movieNameList = listOf("마블", "DC"),
                    ),
                    PlaceDetailsEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        starCount = 4.5f,
                        reviewCount = 123,
                        likeCount = 456,
                        movieNameList = listOf("마블", "DC"),
                    ),
                    PlaceDetailsEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        starCount = 4.5f,
                        reviewCount = 123,
                        likeCount = 456,
                        movieNameList = listOf("마블", "DC"),
                    ),
                ),
            onBackClick = {},
        )
    }
}
