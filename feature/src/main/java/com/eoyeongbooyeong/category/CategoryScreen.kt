package com.eoyeongbooyeong.category

import SortingDropdownMenu
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.entity.PlaceEntity
import com.eoyeongbooyeong.feature.R
import com.eoyeongbooyeong.search.component.PlaceInfoListItem

@Composable
fun PlaceCategoryRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    searchResultList: List<PlaceEntity> = emptyList(),
) {
    PlaceCategoryScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        searchResultList = searchResultList,
    )
}

@Composable
fun PlaceCategoryScreen(
    modifier: Modifier,
    searchResultList: List<PlaceEntity>,
    onBackClick: () -> Unit,
    onSortSelected: (String) -> Unit = {},
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val tabItemTitle =
        listOf(
            stringResource(R.string.movie),
            stringResource(R.string.local),
            stringResource(R.string.tour),
        )

    Column(
        modifier = modifier.fillMaxSize().background(White).statusBarsPadding().systemBarsPadding(),
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
        SortingDropdownMenu(onSortSelected = onSortSelected)
        PlaceList(
            searchResultList = searchResultList,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun PlaceList(
    modifier: Modifier = Modifier,
    searchResultList: List<PlaceEntity>,
    onPlaceClick: (PlaceEntity) -> Unit = {},
) {
    LazyColumn {
        items(searchResultList) { place ->
            PlaceInfoListItem(
                placeName = place.name,
                address = place.address,
                star = place.star,
                reviewCount = place.reviewCount,
                likedCount = place.likedCount,
                movieNameList = place.movieNameList,
                placeImageUrl = place.imageUrl,
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
                listOf(
                    PlaceEntity(
                        name = "피자헛",
                        address = "서울특별시 강남구 역삼동 123-456",
                        star = 4.5f,
                        reviewCount = 123,
                        likedCount = 456,
                        movieNameList = listOf("마블", "DC"),
                        imageUrl = "https://image.com",
                    ),
                    PlaceEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        star = 4.5f,
                        reviewCount = 123,
                        likedCount = 456,
                        movieNameList = listOf("마블", "DC"),
                        imageUrl = "https://image.com",
                    ),
                    PlaceEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        star = 4.5f,
                        reviewCount = 123,
                        likedCount = 456,
                        movieNameList = listOf("마블", "DC"),
                        imageUrl = "https://image.com",
                    ),
                    PlaceEntity(
                        name = "도미노피자",
                        address = "서울특별시 강남구 역삼동 123-456",
                        star = 4.5f,
                        reviewCount = 123,
                        likedCount = 456,
                        movieNameList = listOf("마블", "DC"),
                        imageUrl = "https://image.com",
                    ),
                ),
            onBackClick = {},
        )
    }
}