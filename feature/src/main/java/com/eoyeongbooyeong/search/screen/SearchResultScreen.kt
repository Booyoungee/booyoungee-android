package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.feature.R

@Composable
fun SearchResultScreen() {
    val selectedIndex = remember { mutableStateOf(0) }
    val tabItemTitle =
        listOf(
            stringResource(R.string.movie),
            stringResource(R.string.local),
            stringResource(
                R.string.restaurant,
            ),
        )
    Column(modifier = Modifier.fillMaxWidth()) {
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
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { selectedIndex.value = index }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = tabItemTitle[index],
                            style = BooTheme.typography.caption1
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun SearchResultScreenPreview() {
    SearchResultScreen()
}