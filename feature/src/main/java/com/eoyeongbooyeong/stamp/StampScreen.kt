package com.eoyeongbooyeong.stamp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import kotlinx.coroutines.launch

@Composable
internal fun StampScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        BooTextTopAppBar(
            text = "스탬프",
        )

        val tabData = listOf("1번", "2번")
        val pagerState = rememberPagerState(initialPage = 0) { tabData.size }
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier,
            containerColor = White,
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
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(if (index == 0) "1번 스탬프" else "2번 스탬프")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStampScreen() {
    BooTheme {
        StampScreen()
    }
}
