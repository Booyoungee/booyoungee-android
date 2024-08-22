package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.Place

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
    resultCount: Int = 0,
    searchResultList: List<Place> = emptyList(),
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 43.dp, bottom = 12.dp)
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
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        modifier = Modifier.background(White),
    )
}
