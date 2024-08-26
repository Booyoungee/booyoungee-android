package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray300
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun HotTravelDestinationsRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    onActiveChange: (Boolean) -> Unit = {},
    query: String = "",
    active: Boolean = false,
    searchResultTime: String = "",
    hotTravelDestinations: List<String> = emptyList(),
) {
    HotTravelDestinationsScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        query = query,
        onQueryChange = onQueryChange,
        active = active,
        onActiveChange = onActiveChange,
        searchResultTime = searchResultTime,
        hotTravelDestinations = hotTravelDestinations,
    )
}

@Composable
fun HotTravelDestinationsScreen(
    modifier: Modifier,
    searchResultTime: String,
    hotTravelDestinations: List<String>,
    onBackClick: () -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
) {
    Column(
        modifier =
            modifier
                .background(White)
                .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.CenterHorizontally),
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

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        ) {
            itemsIndexed(hotTravelDestinations) { index, _ ->
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
                            color = if (index < 3) Blue300 else Black,
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
        HotTravelDestinationsRoute(
            query = "제주도",
            searchResultTime = "2021.10.01",
            hotTravelDestinations = listOf("제주도", "부산", "강릉", "경주", "제주도", "부산", "강릉", "경주"),
        )
    }
}
