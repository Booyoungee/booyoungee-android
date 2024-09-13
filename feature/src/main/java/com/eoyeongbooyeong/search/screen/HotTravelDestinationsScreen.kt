package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.domain.entity.HotPlaceEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HotTravelDestinationsScreen(
    modifier: Modifier,
    hotTravelDestinationsFetchTime: String,
    hotTravelDestinations: ImmutableList<HotPlaceEntity>,
    clickHotPlace: (String) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "지금 핫한 여행지",
                modifier = Modifier.weight(1f, false),
                style = BooTheme.typography.body1,
                color = Black,
            )
            Text(
                text = hotTravelDestinationsFetchTime,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = BooTheme.typography.caption4,
                color = Gray300,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        ) {
            itemsIndexed(hotTravelDestinations) { rank, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .noRippleClickable(onClick = { clickHotPlace(item.name) })
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
                            text = "${rank + 1}",
                            textAlign = TextAlign.Center,
                            style = BooTheme.typography.body1,
                            color = if (rank < 3) Blue300 else Black,
                        )
                    }

                    Text(
                        text = item.name,
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

@Preview(showBackground = true)
@Composable
fun HotTravelDestinationsScreenPreview() {
    BooTheme {
        HotTravelDestinationsScreen(
            modifier = Modifier,
            hotTravelDestinationsFetchTime = "2021.10.01",
            hotTravelDestinations = persistentListOf(
                HotPlaceEntity(
                    placeId = 1,
                    type = "HOT",
                    name = "제주도",
                    updatedAt = "2021.10.01",
                    viewCount = 100,
                ),
                HotPlaceEntity(
                    placeId = 2,
                    type = "HOT",
                    name = "부산",
                    updatedAt = "2021.10.01",
                    viewCount = 100,
                ),
                HotPlaceEntity(
                    placeId = 3,
                    type = "HOT",
                    name = "강릉",
                    updatedAt = "2021.10.01",
                    viewCount = 100,
                ),
                HotPlaceEntity(
                    placeId = 4,
                    type = "HOT",
                    name = "경주",
                    updatedAt = "2021.10.01",
                    viewCount = 100,
                ),
                HotPlaceEntity(
                    placeId = 5,
                    type = "HOT",
                    name = "전주",
                    updatedAt = "2021.10.01",
                    viewCount = 100,
                ),
            ),
        )
    }
}
