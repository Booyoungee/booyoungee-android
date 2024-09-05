package com.eoyeongbooyeong.new_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.star.ReviewStar
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.Place
import com.eoyeongbooyeong.feature.R

@Composable
internal fun NewHomeRoute(
    paddingValues: PaddingValues = PaddingValues(0.dp), // 추후 네비 연결시 기본값 제거 및 받아올 예정
) {
    NewHomeScreen(
        paddingValues = paddingValues
    )
}

@Composable
private fun NewHomeScreen(
    paddingValues: PaddingValues,
) {
    val verticalScrollState = rememberScrollState()
    val horizontalRecommendedScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(paddingValues)
            .verticalScroll(verticalScrollState, true),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {


            BooSearchTextField(
                modifier = Modifier.padding(vertical = 12.dp),
                isActive = false,
                onClick = {}, // navigate to search screen
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ImageWithText(
                    painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_movie_cover),
                    title = "영화",
                    description = "영화와 함께 하는\n부산의 특별한 순간",
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    ImageWithText(
                        painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_local_cover),
                        title = "지역상생",
                        description = "부산의 매력을 더하는\n숨은 명소들",
                        alignment = Alignment.TopStart
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ImageWithText(
                        painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_food_cover),
                        title = "영화",
                        description = "영화의 감동을 이어줄\n맛있는 여정",
                        alignment = Alignment.TopStart
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "부산 유명 장소를 추천해 드릴게요",
                style = BooTheme.typography.head3,
                color = Black
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(horizontalRecommendedScrollState),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(5) { index ->
                if (index == 0) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
                RecommendedPlaceItem(
                    place = Place(
                        name = "부산 국제영화제",
                        star = 4.5f,
                        reviewCount = 100,
                        likedCount = 100,
                    ),
                )
                if (index == 4) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            Modifier
                .padding(horizontal = 24.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Brush.linearGradient(listOf(Color(0xFFC9E2D6), Color(0xFFC8D6EB))))
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_popcorn),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
            )

            Spacer(modifier = Modifier.width(4.dp))

            Column {
                Text(text = "놓칠 수 없는 영화제 소식", style = BooTheme.typography.body1, color = Black)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "영화제의 가장 뜨거운 이야기를\n빠짐없이 챙겨보세요!", style = BooTheme.typography.body4, color = Black)
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Composable
fun ImageWithText(
    painter: Painter,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.BottomStart,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .align(alignment)
                .padding(horizontal = 12.dp)
                .padding(bottom = 19.dp, top = 8.dp)
        ) {
            Text(
                text = title,
                style = BooTheme.typography.body1,
                color = White
            )
            Text(
                text = description,
                style = BooTheme.typography.caption1,
                color = White
            )
        }

    }
}

@Composable
fun RecommendedPlaceItem(
    place: Place,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_default_5),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = place.name,
            style = BooTheme.typography.body1,
            color = Black
        )
        Spacer(modifier = Modifier.height(2.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            ReviewStar(place.star)

            Text(
                text = place.star.toString(),
                style = BooTheme.typography.caption1,
                color = Black,
                modifier =
                modifier
                    .padding(start = 4.dp),
            )
            Text(
                text = stringResource(R.string.placeReviewAndPoint, place.reviewCount),
                style = BooTheme.typography.caption2,
                color = Gray400,
                modifier =
                modifier
                    .padding(start = 4.dp),
            )
            Image(
                painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_like),
                contentDescription = "liked icon",
                modifier = Modifier.size(12.dp),
            )
            Text(
                text = place.likedCount.toString(),
                style = BooTheme.typography.caption1,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier =
                modifier
                    .padding(start = 4.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewHomeScreenPreview() {
    BooTheme {
        NewHomeScreen(PaddingValues(0.dp))
    }
}
