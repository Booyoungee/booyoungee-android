package com.eoyeongbooyeong.places.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.component.button.BooLargeButton
import com.eoyeongbooyeong.core.designsystem.component.star.ClickableReviewStar
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.Red
import com.eoyeongbooyeong.core.designsystem.theme.White
import timber.log.Timber

@Composable
fun ReviewRoute(
    placeId: Int = 898,
    viewModel: WritingReviewViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()
    val isStarWarning = state.value.reviewStars == 0
    val isTextWarning = state.value.reviewTextContent.isEmpty()

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects
            .flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is WritingReviewSideEffect.NavigateReviewFinish -> {
                        // 리뷰 작성 완료 후 화면 종료
                    }

                    else -> {
                        // do nothing
                    }
                }
            }
    }

    ReviewScreen(
        onValueChange = { newText ->
            viewModel.updateState(newState = state.value.copy(reviewTextContent = newText))
        },
        reviewText = state.value.reviewTextContent ?: "",
        onClickReviewScore = { newRating ->
            viewModel.updateState(newState = state.value.copy(reviewStars = newRating))
        },
        onReviewSubmitClick = {
            Timber.d("postReview clicked")
            viewModel.postReview(
                placeId = placeId,
                content = state.value.reviewTextContent,
                stars = state.value.reviewStars,
            )
        },
        isStarWarning = isStarWarning,
        isTextWarning = isTextWarning,
    )
}

@Composable
fun ReviewScreen(
    onValueChange: (String) -> Unit,
    onReviewSubmitClick: () -> Unit,
    reviewText: String,
    isStarWarning: Boolean,
    isTextWarning: Boolean,
    onClickReviewScore: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .statusBarsPadding()
            .systemBarsPadding(),
    ) {
        BooTextTopAppBar(
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_cancel),
                    contentDescription = "left",
                )
            },
            text = "리뷰 작성하기",
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "방문 후기를 알려주세요!",
                style = BooTheme.typography.head3,
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            ClickableReviewStar(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClickReviewScore = {
                    onClickReviewScore(it)
                },
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "별점을 선택해주세요.",
                style = BooTheme.typography.caption2,
                color = if (isStarWarning) Red else Gray400,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(24.dp))
            BasicTextField(
                value = reviewText,
                onValueChange = onValueChange,
                modifier = Modifier
                    .sizeIn(minHeight = 310.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Gray100)
                    .padding(8.dp),
                textStyle = BooTheme.typography.body4,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        innerTextField()
                        if (reviewText.isEmpty()) {
                            Text(
                                text = "장소에 대한 후기를 작성해 주세요.",
                                style = BooTheme.typography.body4,
                                color = Gray400,
                            )
                        }
                    }
                },
            )

            if (isTextWarning) {
                Text(
                    text = "후기를 작성해 주세요.",
                    style = BooTheme.typography.caption2,
                    color = Red,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            BooLargeButton(
                text = "등록하기",
                onClick = onReviewSubmitClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                enabled = !isStarWarning && !isTextWarning,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
@Preview
fun ReviewScreenPreview() {
    BooTheme {
        ReviewScreen(
            onValueChange = {},
            onReviewSubmitClick = {},
            reviewText = "",
            isStarWarning = false,
            isTextWarning = false,
            onClickReviewScore = {},
        )
    }
}
