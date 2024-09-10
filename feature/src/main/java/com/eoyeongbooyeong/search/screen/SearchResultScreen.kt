package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.entity.PlaceEntity
import com.eoyeongbooyeong.feature.R
import com.eoyeongbooyeong.search.component.PlaceInfoListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SearchResultScreen(
    modifier: Modifier = Modifier,
    resultCount: Int = 0,
    searchResultList: ImmutableList<PlaceEntity>,
) {
    Column(
        modifier =
        modifier
            .fillMaxSize()
            .background(White),
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        SearchResultList(
            searchResultList = searchResultList,
            resultCount = resultCount,
        ) {
            // Handle item click
        }
    }
}

@Composable
fun SearchResultList(
    searchResultList: List<PlaceEntity>,
    resultCount: Int,
    modifier: Modifier = Modifier,
    onPlaceClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
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
            modifier = Modifier.fillMaxWidth(),
        ) {
            item {
                Text(
                    text = annotatedString,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(
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
                    onClick = onPlaceClick,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
            }
        }
    }
}

@Composable
@Preview
fun SearchResultScreenPreview() {
    BooTheme {
        SearchResultScreen(
            resultCount = 10,
            searchResultList = persistentListOf(
                PlaceEntity(
                    name = "플레이스 이름",
                    address = "주소",
                    star = 4f,
                    reviewCount = 100,
                    likedCount = 100,
                    movieNameList = listOf("영화1", "영화2"),
                    imageUrl = "https://place.image.url",
                ),
            ),
        )
    }
}
