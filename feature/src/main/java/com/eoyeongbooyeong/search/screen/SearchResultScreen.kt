package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import com.eoyeongbooyeong.feature.R
import com.eoyeongbooyeong.search.component.PlaceInfoListItem

@Composable
fun SearchResultRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    onActiveChange: (Boolean) -> Unit = {},
    query: String = "",
    active: Boolean = false,
    resultCount: Int = 0,
    searchResultList: List<PlaceDetailsEntity> = emptyList(),
) {
    SearchResultScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onQueryChange = onQueryChange,
        onActiveChange = onActiveChange,
        query = query,
        active = active,
        resultCount = resultCount,
        searchResultList = searchResultList,
    )
}

@Composable
fun SearchResultScreen(
    modifier: Modifier,
    resultCount: Int = 0,
    searchResultList: List<PlaceDetailsEntity>,
    onBackClick: () -> Unit,
    onQueryChange: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    query: String,
    active: Boolean,
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val tabItemTitle =
        listOf(
            stringResource(R.string.movie),
            stringResource(R.string.local),
            stringResource(
                R.string.restaurant,
            ),
        )

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(White),
    ) {
        Spacer(modifier = Modifier.padding(12.dp))

        Box(
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            Row(
                modifier =
                    modifier
                        .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                    contentDescription = "back button",
                    modifier =
                        Modifier
                            .padding(6.dp)
                            .clickable(onClick = onBackClick)
                            .size(24.dp)
                            .align(Alignment.CenterVertically),
                )
                BooSearchTextField(
                    text = query,
                    onValueChange = onQueryChange,
                    isActive = active,
                    onClick = { onActiveChange(true) },
                    modifier = Modifier.weight(1f),
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TabRow(
            selectedTabIndex = selectedIndex.value,
            containerColor = Transparent,
            contentColor = Black,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex.value]),
                    color = Blue300,
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
                        )
                    }
                }
            }
        }
        SearchResultList(
            searchResultList = searchResultList,
            resultCount = resultCount,
        )
    }
}

@Composable
fun SearchResultList(
    modifier: Modifier = Modifier,
    searchResultList: List<PlaceDetailsEntity>,
    resultCount: Int,
    onPlaceClick: (PlaceDetailsEntity) -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
    ) {
        val annotatedString =
            buildAnnotatedString {
                withStyle(
                    style =
                        BooTheme.typography.caption1
                            .toSpanStyle()
                            .copy(color = Purple),
                ) {
                    append(resultCount.toString())
                }
                withStyle(
                    style =
                        BooTheme.typography.caption2
                            .toSpanStyle()
                            .copy(color = Black),
                ) {
                    append(stringResource(R.string.searchResultCount))
                }
            }

        LazyColumn(
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) {
            item {
                Text(
                    text = annotatedString,
                    textAlign = TextAlign.Start,
                    modifier =
                        Modifier.padding(
                            bottom = 12.dp,
                            start = 24.dp,
                            end = 24.dp,
                            top = 12.dp,
                        ),
                )
            }

            items(searchResultList) { place ->
                PlaceInfoListItem(
                    placeName = place.name,
                    address = place.address,
                    star = place.starCount,
                    reviewCount = place.reviewCount,
                    likedCount = place.likeCount,
                    movieNameList = place.movieNameList,
                    placeImageUrl = place.imageUrl,
                    onClick = { /* Handle item click */ },
                    modifier = Modifier.padding(bottom = 16.dp, start = 24.dp, end = 24.dp),
                )
            }
        }
    }
}

@Composable
@Preview
fun SearchResultScreenPreview() {
    BooTheme {
        SearchResultRoute()
    }
}
