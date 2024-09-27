package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.eoyeongbooyeong.core.designsystem.component.LoadingWithProgressIndicator
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import com.eoyeongbooyeong.feature.R
import com.eoyeongbooyeong.search.component.PlaceInfoListItem

@Composable
fun SearchResultScreen(
    modifier: Modifier = Modifier,
    resultCount: Int = 0,
    isLoading: Boolean = false,
    searchResultList: LazyPagingItems<PlaceDetailsEntity>,
    onClickPlace: (Int, String) -> Unit,
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
            isLoading = isLoading,
            resultCount = resultCount,
        ) {
            onClickPlace(it.placeId.toIntOrNull() ?: 0, it.type)
        }
    }
}

@Composable
fun SearchResultList(
    searchResultList: LazyPagingItems<PlaceDetailsEntity>,
    resultCount: Int,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onPlaceClick: (PlaceDetailsEntity) -> Unit = {},
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

            items(searchResultList.itemCount) { index ->
                PlaceInfoListItem(
                    placeName = searchResultList[index]?.name ?: "",
                    address = searchResultList[index]?.address ?: "",
                    star = searchResultList[index]?.starCount ?: 0.0,
                    reviewCount = searchResultList[index]?.reviewCount ?: 0,
                    likedCount = searchResultList[index]?.likeCount ?: 0,
                    movieNameList = searchResultList[index]?.movieNameList,
                    modifier = Modifier.padding(bottom = 16.dp, start = 24.dp, end = 24.dp),
                    placeImageUrl = searchResultList[index]?.imageUrl?.firstOrNull(),
                    showBookmarkIcon = false,
                    onClick = { onPlaceClick(searchResultList[index] ?: PlaceDetailsEntity()) },
                )
            }

            item {
                if (isLoading) {
                    LoadingWithProgressIndicator()
                }
            }
        }
    }
}

//@Composable
//@Preview
//fun SearchResultScreenPreview() {
//    BooTheme {
//        SearchResultScreen(
//            modifier = Modifier.fillMaxSize(),
//            resultCount = 20,
//            searchResultList = persistentListOf(
//                // TODO: 임시 데이터
//                PlaceDetailsEntity(
//                    address = "서울특별시 강남구 역삼동 123-456",
//                    reviewCount = 123,
//                    movieNameList = listOf("피자헛"),
//                ),
//                PlaceDetailsEntity(
//                    address = "서울특별시 강남구 역삼동 123-456",
//                    reviewCount = 123,
//                    movieNameList = listOf("피자헛"),
//                ),
//                PlaceDetailsEntity(
//                    address = "서울특별시 강남구 역삼동 123-456",
//                    reviewCount = 123,
//                    movieNameList = listOf("피자헛"),
//                ),
//            ),
//            onClickPlace = { _, _ -> },
//        )
//    }
//}
