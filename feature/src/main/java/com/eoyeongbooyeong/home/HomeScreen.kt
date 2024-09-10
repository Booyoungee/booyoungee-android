package com.eoyeongbooyeong.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import com.eoyeongbooyeong.core.constant.BusanInternationalFilmFestival
import com.eoyeongbooyeong.core.designsystem.component.star.ReviewStar
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.feature.R

@Composable
internal fun HomeRoute(
    paddingValues: PaddingValues,
    navigateToSearch: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.NavigateToWebView -> {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(sideEffect.url)))
                    }

                    HomeSideEffect.NavigateToSearch -> navigateToSearch()
                }
            }
    }

    HomeScreen(
        paddingValues = paddingValues,
        recommendedPlace = state.value.recommendedPlace,
        navigateToWebView = viewModel::navigateToWebView,
        navigateToSearch = viewModel::navigateToSearch,
    )
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    recommendedPlace: List<PlaceInfoEntity> = emptyList(),
    navigateToWebView: (String) -> Unit = {},
    navigateToSearch: () -> Unit = {},
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
                onClick = navigateToSearch
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
                        painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_tour_cover),
                        title = "관광지",
                        description = "다양한 매력이 숨 쉬는\n부산 필수 관광지",
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
            repeat(recommendedPlace.size) { index ->
                if (index == 0) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
                RecommendedPlaceItem(
                    place = recommendedPlace[index],
                )
                if (index == recommendedPlace.size - 1) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            Modifier
                .noRippleClickable {
                    navigateToWebView(BusanInternationalFilmFestival)
                }
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
                Text(
                    text = "영화제의 가장 뜨거운 이야기를\n빠짐없이 챙겨보세요!",
                    style = BooTheme.typography.body4,
                    color = Black
                )
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
    place: PlaceInfoEntity,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.width(160.dp),
    ) {
        AsyncImage(
            place.images.firstOrNull() ?: com.eoyeongbooyeong.core.R.drawable.img_default_5,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(10.dp)),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = place.name,
            style = BooTheme.typography.body1,
            color = Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(2.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            ReviewStar(place.starCount)

            Text(
                text = place.starCount.toString(),
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
                text = place.likeCount.toString(),
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
        HomeScreen(PaddingValues(0.dp))
    }
}
