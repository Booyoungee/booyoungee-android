package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray300
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun HotTravelDestinationsScreen(
    modifier: Modifier = Modifier,
    searchResultTime: String,
    hotTravelDestinations: List<String> = listOf(),
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "지금 핫한 여행지",
                modifier = Modifier.weight(1f, false),
                style = BooTheme.typography.body1,
                color = Black,
            )
            Text(
                text = searchResultTime,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = BooTheme.typography.caption4,
                color = Gray300,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(hotTravelDestinations.size) { index ->
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier =
                            Modifier
                                .width(24.dp)
                                .wrapContentHeight(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "${index + 1}",
                            textAlign = TextAlign.Center,
                            style = BooTheme.typography.body1,
                            color = if (index < 4) Blue300 else Black,
                        )
                    }

                    Text(
                        text = hotTravelDestinations.getOrNull(index) ?: "",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start,
                        style = BooTheme.typography.body4,
                        color = Black,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun HotTravelDestinationsScreenPreview() {
    BooTheme {
        HotTravelDestinationsScreen(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(White),
            searchResultTime = "2024년 7월 6일 00:00기준",
            hotTravelDestinations =
                listOf(
                    "제주도",
                    "부산",
                    "강릉",
                    "경주",
                    "전주",
                    "여수",
                    "포항",
                    "대구",
                    "서울",
                    "인천",
                ),
        )
    }
}
