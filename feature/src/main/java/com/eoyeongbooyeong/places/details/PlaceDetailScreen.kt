package com.eoyeongbooyeong.places.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.component.WhiteLoadingWithProgressIndicator
import com.eoyeongbooyeong.core.designsystem.component.star.Review
import com.eoyeongbooyeong.core.designsystem.component.star.ReviewStar
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.formatReviewDate
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.core.extension.toast
import com.eoyeongbooyeong.domain.entity.ReviewInfoEntity
import com.eoyeongbooyeong.search.component.PlaceDetailBottomBar
import com.eoyeongbooyeong.search.component.PlaceDetailInfo
import com.eoyeongbooyeong.search.component.PlaceReviewAndLikedCount

@Composable
fun PlaceDetailRoute(
    placeId: Int = 898,
    placeType: String = "movie",
    onClickWriteReview: (Int, String) -> Unit = { _, _ -> },
    onClickBackButton: () -> Unit = {},
    viewModel: PlaceDetailsViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val state = viewModel.state.collectAsStateWithLifecycle()
    val placeInfoEntity = state.value.placeInfoEntity

    LaunchedEffect(key1 = Unit) {
        viewModel.getPlaceDetailsInfo(placeId = placeId, placeType = placeType)
        viewModel.getReviews(placeId = placeId)
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects
            .flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is PlaceDetailsSideEffect.ShowToast -> {
                        context.toast(sideEffect.message)
                    }

                    else -> {}
                }
            }
    }

    PlaceDetailScreen(
        placeId = placeId,
        modifier = Modifier,
        movieList = placeInfoEntity.movies?.joinToString(", ") ?: "",
        movieTitle = placeInfoEntity.name,
        tel = placeInfoEntity.tel,
        imageUrl = placeInfoEntity.images,
        stampCount = placeInfoEntity.stampCount,
        placeAddress = placeInfoEntity.address,
        placeDetailReviewCount = placeInfoEntity.reviewCount,
        placeDetailLikedCount = state.value.likeCount,
        placeDetailStarScore = placeInfoEntity.stars,
        placeDetailBookmarkCount = state.value.bookMarkCount,
        reviewInfoEntityTotalList = state.value.reviewList,
        onClickWriteReview = { onClickWriteReview(placeId, placeType) },
        onClickLike = {
            if (state.value.isLiked) {
                viewModel.deleteLike(placeId = placeId)
            } else {
                viewModel.postLike(placeId = placeId)
            }
        },
        onClickBookmark = {
            if (state.value.isBookmarked) {
                viewModel.deleteBookMark(placeId = placeId)
            } else {
                viewModel.postBookMark(placeId = placeId, placeType = placeType)
            }
        },
        onClickBackButton = onClickBackButton,
        isLike = (state.value.isLiked),
        isBookmark = (state.value.isBookmarked),
        onBlockClick = { blockUserId ->
            viewModel.postBlockUser(blockUserId = blockUserId, placeId = placeId)
        },
        onAccuseClick = { reviewId ->
            viewModel.postAccuseReview(commentId = reviewId)
        },
        isLoading = state.value.isLoading,
    )
}

@Composable
fun PlaceDetailScreen(
    placeId: Int,
    modifier: Modifier = Modifier,
    movieTitle: String = "",
    imageUrl: List<String> = emptyList(),
    stampCount: Int = 0,
    placeAddress: String = "",
    movieList: String = "",
    tel: String? = null,
    placeDetailReviewCount: Int = 0,
    placeDetailLikedCount: Int = 0,
    placeDetailStarScore: Double = 0.0,
    placeDetailBookmarkCount: Int = 0,
    reviewInfoEntityTotalList: List<ReviewInfoEntity> = emptyList(),
    onClickWriteReview: (Int) -> Unit = {},
    onClickLike: () -> Unit = {},
    onClickBookmark: () -> Unit = {},
    onClickBackButton: () -> Unit,
    isLike: Boolean = false,
    isBookmark: Boolean = false,
    onBlockClick: (Int) -> Unit = {},
    onAccuseClick: (Int) -> Unit = {},
    isLoading: Boolean = false,
) {
    Scaffold(
        modifier =
            modifier
                .fillMaxSize()
                .systemBarsPadding()
                .statusBarsPadding(),
        topBar = {
            BooTextTopAppBar(
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
                        contentDescription = "left",
                    )
                },
                text = movieTitle,
                modifier = modifier.noRippleClickable(onClick = onClickBackButton),
            )
        },
        bottomBar = {
            PlaceDetailBottomBar(
                onClickLike = onClickLike,
                onClickBookmark = onClickBookmark,
                onClickWriteReview = { onClickWriteReview(placeId) },
                likeCount = placeDetailLikedCount,
                bookmarkCount = placeDetailBookmarkCount,
                isLike = isLike,
                isBookmark = isBookmark,
            )
        },
    ) { innerPadding ->

        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(White),
        ) {
            item {
                if (imageUrl.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_default_5),
                            contentDescription = "Default Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(280.dp)
                                .height(280.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = if (imageUrl.size == 1) Arrangement.Center else Arrangement.Start
                    ) {
                        itemsIndexed(imageUrl) { index, imageUrl ->
                            AsyncImage(
                                model = imageUrl,
                                placeholder = painterResource(R.drawable.default_white_img),
                                contentDescription = "PlaceDetailsEntity Detail Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(320.dp)
                                    .padding(start = 10.dp, end = 10.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                error = painterResource(R.drawable.img_default_5)
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 28.dp, start = 24.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = movieTitle, style = BooTheme.typography.head2, color = Black)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_stamp_boo),
                            contentDescription = "stamp icon",
                            modifier = Modifier.size(48.dp),
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = stampCount.toString(),
                            style = BooTheme.typography.caption1,
                            color = Black,
                        )
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Text(
                        text = placeAddress,
                        style = BooTheme.typography.body4,
                        color = Black,
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    PlaceReviewAndLikedCount(
                        reviewCount = placeDetailReviewCount,
                        likedCount = placeDetailLikedCount,
                        star = placeDetailStarScore,
                    )

                    Spacer(modifier = Modifier.height(26.dp))
                    if (movieList.isNotEmpty()) {
                        PlaceDetailInfo(
                            icon = painterResource(id = R.drawable.ic_film),
                            iconDescription = "film icon",
                            text = movieList,
                            style = BooTheme.typography.body3,
                        )
                    }

                    if (tel?.isNotBlank() == true) {
                        PlaceDetailInfo(
                            icon = painterResource(id = R.drawable.ic_call),
                            iconDescription = "call info",
                            text = tel,
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                Spacer(
                    modifier =
                        Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                            .background(Gray100),
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp),
                ) {
                    Text(
                        text = "리뷰",
                        style = BooTheme.typography.head3,
                        color = Black,
                        textAlign = TextAlign.Start,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = placeDetailStarScore.toString(),
                            style = BooTheme.typography.head3,
                            color = Black,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        ReviewStar(star = placeDetailStarScore, starSize = 30.dp)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }

            itemsIndexed(reviewInfoEntityTotalList) { index, review ->
                Column(
                    modifier = Modifier.padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
                ) {
                    Review(
                        reviewId = review.id,
                        writerId = review.writerId,
                        nickName = review.writerNickName,
                        reviewScore = review.reviewScore,
                        reviewContent = review.reviewContent,
                        reviewDate = formatReviewDate(review.createdAt),
                        onBlockClick = onBlockClick,
                        onReportClick = onAccuseClick,
                    )
                }
            }
        }

        if (isLoading) {
            WhiteLoadingWithProgressIndicator()
        }
    }
}

@Composable
@Preview
fun PlaceDetailScreenPreview() {
    BooTheme {
        PlaceDetailScreen(
            movieTitle = "영화 제목",
            imageUrl = listOf("https://picsum.photos/200/300"),
            stampCount = 10,
            placeAddress = "부산 기장군 철마면 웅천리 520-10",
            placeDetailReviewCount = 10,
            placeDetailLikedCount = 20,
            placeDetailStarScore = 4.5,
            reviewInfoEntityTotalList =
                listOf(
                    ReviewInfoEntity(
                        id = 1,
                        placeId = 1,
                        writerId = 1,
                        writerNickName = "User1",
                        reviewScore = 4.5,
                        reviewContent = "너무 좋아요 너무 좋아요 너무 좋아요 너무 좋아요 너무 좋아요 너무 좋아요너무 좋아요너무 좋아요",
                        createdAt = "2024-01-01",
                    ),
                    ReviewInfoEntity(
                        id = 1,
                        placeId = 1,
                        writerId = 1,
                        writerNickName = "User1",
                        reviewScore = 4.5,
                        reviewContent = "너무 좋아요",
                        createdAt = "2024-01-01",
                    ),
                    ReviewInfoEntity(
                        id = 1,
                        placeId = 1,
                        writerId = 1,
                        writerNickName = "User1",
                        reviewScore = 4.5,
                        reviewContent = "너무 좋아요",
                        createdAt = "2024-01-01",
                    ),
                    ReviewInfoEntity(
                        id = 1,
                        placeId = 1,
                        writerId = 1,
                        writerNickName = "User1",
                        reviewScore = 4.5,
                        reviewContent = "너무 좋아요",
                        createdAt = "2024-01-01",
                    ),
                ),
            onClickWriteReview = {},
            onClickLike = {},
            onClickBookmark = {},
            onClickBackButton = {},
            isLike = true,
            isBookmark = true,
            placeId = 1,
        )
    }
}
