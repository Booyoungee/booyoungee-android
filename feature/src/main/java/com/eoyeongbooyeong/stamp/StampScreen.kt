package com.eoyeongbooyeong.stamp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.stamp.component.StampItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Composable
internal fun StampRoute(
    paddingValues: PaddingValues,
    viewModel: StampViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    StampScreen(
        paddingValues = paddingValues,
        userName = state.userName,
        stampList = state.stampList,
        collectedStampList = state.collectedStampList,
    )
}

@Composable
private fun StampScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    userName: String = "부영이",
    stampList: ImmutableList<String> = persistentListOf(),
    collectedStampList: ImmutableList<String> = persistentListOf(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        BooTextTopAppBar(
            text = "스탬프",
        )

        val tabData = listOf("만날 수 있는 부영이", "${userName}님의 부영이")
        val pagerState = rememberPagerState(initialPage = 0) { tabData.size }
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier,
            containerColor = White,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Purple
                )
            }
        ) {
            tabData.forEachIndexed { index, text ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = text,
                            style = BooTheme.typography.caption1,
                            color = if (pagerState.currentPage == index) {
                                Black
                            } else {
                                Gray400
                            }
                        )
                    },
                    modifier = Modifier.background(White)
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.background(White)
        ) { index ->
            Column(
                modifier = Modifier
                    .paint(
                        painter = painterResource(com.eoyeongbooyeong.core.R.drawable.img_stamp_background),
                        contentScale = ContentScale.FillBounds
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CollectedStampCard(
                    stampCount = collectedStampList.size
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(28.dp),
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                ) {
                    items(
                        if (index == 0) stampList else collectedStampList
                    ) { item ->
                        StampItem(
                            imageUrl = null,
                            text = item,
                            isLocked = index == 1
                        )
                    }
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CollectedStampCard(
    stampCount: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = White
        )
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "내가 모은 부영이",
            style = BooTheme.typography.body3,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stampCount.toString(),
                style = BooTheme.typography.head1,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "마리",
                style = BooTheme.typography.body2,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStampScreen() {
    BooTheme {
        StampScreen()
    }
}
