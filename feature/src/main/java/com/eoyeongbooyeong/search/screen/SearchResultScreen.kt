package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.domain.Place
import com.eoyeongbooyeong.feature.R
import com.eoyeongbooyeong.search.component.PlaceInfoListItem

@Composable
fun SearchResultScreen(
    resultCount: Int = 0,
    searchResultList: List<Place> = emptyList(),
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
        modifier = Modifier.fillMaxWidth(),
    ) {
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
                                .padding(16.dp),
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
    searchResultList: List<Place>,
    resultCount: Int,
    onSearchResultClick: (Place) -> Unit = {},
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
                    star = place.star,
                    reviewCount = place.reviewCount,
                    likedCount = place.likedCount,
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
    SearchResultScreen()
}