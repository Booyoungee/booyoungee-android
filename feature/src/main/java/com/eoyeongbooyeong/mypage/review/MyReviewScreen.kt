package com.eoyeongbooyeong.mypage.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.designsystem.component.star.MyReviewComponent
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.domain.entity.MyReviewEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MyReviewRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToPlaceDetail: (Int, String) -> Unit,
    viewModel: MyReviewViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is MyReviewSideEffect.NavigateUp -> navigateUp()
                    is MyReviewSideEffect.NavigateToPlaceDetail -> navigateToPlaceDetail(
                        sideEffect.placeId,
                        sideEffect.type
                    )
                }
            }
    }

    MyReviewScreen(
        paddingValues = paddingValues,
        reviewList = state.value.myReviewList,
        navigateUp = viewModel::navigateUp,
        navigateToPlaceDetail = viewModel::navigateToPlaceDetail
    )
}

@Composable
fun MyReviewScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    reviewList: ImmutableList<MyReviewEntity> = persistentListOf(),
    navigateUp: () -> Unit = {},
    navigateToPlaceDetail: (Int, String) -> Unit = { _, _ -> }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(White),
    ) {
        BooTextTopAppBar(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                    contentDescription = "left",
                    modifier = Modifier.noRippleClickable(navigateUp)
                )
            },
            text = "내 리뷰"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = reviewList,
                key = { review -> review.id }
            ) { review ->
                MyReviewComponent(
                    reviewId = review.id,
                    writerId = review.writerId,
                    storeName = review.placeName,
                    reviewScore = review.stars.toDouble(),
                    reviewContent = review.content,
                    reviewDate = review.updatedAt.slice(0..10)
                ){
                    navigateToPlaceDetail(review.placeId, review.type)
                }
            }
        }
    }
}

@Preview
@Composable
fun MyReviewsScreenPreview() {
    BooTheme {
        MyReviewScreen()
    }
}
