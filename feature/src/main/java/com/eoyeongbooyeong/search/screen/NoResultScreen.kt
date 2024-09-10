package com.eoyeongbooyeong.search.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.feature.R

@Composable
fun NoResultScreen(
    query: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .background(White),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_alert_circle),
                    contentDescription = "no result icon",
                    modifier =
                    Modifier
                        .size(100.dp)
                        .padding(bottom = 12.dp),
                )

                Text(
                    text = stringResource(R.string.noSearchResult, query),
                    style = BooTheme.typography.body1,
                    color = Black,
                    modifier =
                    Modifier
                        .padding(bottom = 12.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = stringResource(R.string.checkSearchResult),
                    style = BooTheme.typography.body4,
                    color = Gray400,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoResultScreenPreview() {
    BooTheme {
        NoResultScreen(
            query = "검색어"
        )
    }
}
