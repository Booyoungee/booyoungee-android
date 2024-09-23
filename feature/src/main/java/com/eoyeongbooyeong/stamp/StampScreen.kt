package com.eoyeongbooyeong.stamp

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.entity.StampEntity
import com.eoyeongbooyeong.stamp.component.StampItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Composable
internal fun StampRoute(
    paddingValues: PaddingValues,
    navigateToPlaceDetail: (Int, String) -> Unit,
    viewModel: StampViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.getMyStampList()
        viewModel.getNearbyStampList()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is StampSideEffect.ShowToast -> {
                        Toast.makeText(
                            context,
                             "\"${sideEffect.placeName}\" 스탬프를 찍었어요!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }

    StampScreen(
        paddingValues = paddingValues,
        userName = state.userName,
        stampList = state.nearbyStampList,
        collectedStampList = state.myStampList,
        stampPlace = { placeName, placeId, type, mapX, mapY ->
            viewModel.stampPlace(
                placeName,
                placeId,
                type,
                mapX,
                mapY
            )
        },
        navigateToPlaceDetail = navigateToPlaceDetail,
    )
}

@Composable
private fun StampScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    userName: String = "부영이",
    stampList: ImmutableList<StampEntity> = persistentListOf(),
    collectedStampList: ImmutableList<StampEntity> = persistentListOf(),
    stampPlace: (String, Int, String, String, String) -> Unit = { _, _, _, _, _ -> },
    navigateToPlaceDetail: (Int, String) -> Unit = { _, _ -> },
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
                        items = if (index == 0) stampList else collectedStampList,
                        key = { it.placeId }
                    ) { item ->
                        StampItem(
                            imageUrl = item.images.firstOrNull(),
                            text = item.placeName,
                            isLocked = index == 1
                        ) {
                            if (index == 0) {
                                stampPlace(
                                    item.placeName,
                                    item.placeId,
                                    item.type,
                                    item.mapX.toString(),
                                    item.mapY.toString()
                                )
                            } else {
                                navigateToPlaceDetail(
                                    item.placeId,
                                    item.type
                                )
                            }
                        }
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
