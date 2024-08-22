package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    active: Boolean = false,
    onActiveChange: (Boolean) -> Unit = {},
    searchResultTime: String = "",
    hotTravelDestinations: List<String> = emptyList(),
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, top = 43.dp,bottom = 16.dp),
        ) {
            Image(
                painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                contentDescription = "back button",
                modifier =
                    Modifier
                        .padding(6.dp)
                        .clickable(onClick = onBackClick),
            )
            BooSearchTextField(
                text = query,
                onValueChange = onQueryChange,
                isActive = active,
                onClick = { onActiveChange(true) },
                modifier = Modifier.weight(1f),
            )
        }
        HotTravelDestinationsScreen(
            modifier = Modifier.fillMaxSize().padding(top = 12.dp, end = 24.dp, start = 24.dp),
            searchResultTime = searchResultTime,
            hotTravelDestinations = hotTravelDestinations,
        )
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        modifier = Modifier.background(White),
    )
}
