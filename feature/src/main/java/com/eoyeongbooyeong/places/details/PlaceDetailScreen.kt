package com.eoyeongbooyeong.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.component.star.Review
import com.eoyeongbooyeong.core.designsystem.component.star.ReviewStar
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.domain.entity.ReviewInfoEntity
import com.eoyeongbooyeong.search.component.PlaceDetailBottomBar
import com.eoyeongbooyeong.search.component.PlaceDetailInfo
import com.eoyeongbooyeong.search.component.PlaceReviewAndLikedCount

@Composable
fun PlaceDetailRoute(
    modifier: Modifier = Modifier,
    movieTitle: String = "",
    imageUrl: String = "",
    stampCount: Int = 0,
    placeAddress: String = "",
    reviewCount: Int = 0,
    likedCount: Int = 0,
    placeDetailStarScore: Float = 0f,
    placeDetailBookmarkCount: Int = 0,
    reviewInfoEntityTotalList: List<ReviewInfoEntity> = emptyList(),
    onClickWriteReview: () -> Unit = {},
    onClickLike: () -> Unit = {},
    onClickBookmark: () -> Unit = {},
    onClickBackButton: () -> Unit = {},
    isLike: Boolean = false,
    isBookmark: Boolean = false,
) {
    PlaceDetailScreen(
        modifier = modifier,
        movieTitle = movieTitle,
        imageUrl = imageUrl,
        stampCount = stampCount,
        placeAddress = placeAddress,
        placeDetailReviewCount = reviewCount,
        placeDetailLikedCount = likedCount,
        placeDetailStarScore = placeDetailStarScore,
        placeDetailBookmarkCount = placeDetailBookmarkCount,
        reviewInfoEntityTotalList = reviewInfoEntityTotalList,
        onClickWriteReview = onClickWriteReview,
        onClickLike = onClickLike,
        onClickBookmark = onClickBookmark,
        onClickBackButton = onClickBackButton,
        isLike = isLike,
        isBookmark = isBookmark,
    )
}

@Composable
fun PlaceDetailScreen(
    modifier: Modifier = Modifier,
    movieTitle: String,
    imageUrl: String,
    stampCount: Int,
    placeAddress: String,
    placeDetailReviewCount: Int,
    placeDetailLikedCount: Int,
    placeDetailStarScore: Float,
    placeDetailBookmarkCount: Int,
    reviewInfoEntityTotalList: List<ReviewInfoEntity>,
    onClickWriteReview: () -> Unit,
    onClickLike: () -> Unit,
    onClickBookmark: () -> Unit,
    onClickBackButton: () -> Unit,
    isLike: Boolean,
    isBookmark: Boolean,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
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
                onClickWriteReview = onClickWriteReview,
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
                AsyncImage(
                    model =
                        ImageRequest
                            .Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                    placeholder = painterResource(R.drawable.img_default_5),
                    contentDescription = "PlaceDetailsEntity Detail Image",
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(320.dp),
                )
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
                    PlaceDetailInfo(
                        icon = painterResource(id = R.drawable.ic_film),
                        iconDescription = "film icon",
                        text = "<더킹>, <협녀, 칼의 기억>",
                        style = BooTheme.typography.body3,
                    )

                    PlaceDetailInfo(
                        icon = painterResource(id = R.drawable.ic_call),
                        iconDescription = "call info",
                        text = "전용 주차장(무료)",
                    )
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
                        reviewDate = review.createdAt,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PlaceDetailScreenPreview() {
    BooTheme {
        PlaceDetailRoute(
            movieTitle = "영화 제목",
            imageUrl = "https://picsum.photos/200/300",
            stampCount = 10,
            placeAddress = "부산 기장군 철마면 웅천리 520-10",
            reviewCount = 10,
            likedCount = 20,
            placeDetailStarScore = 4.5f,
            reviewInfoEntityTotalList =
                listOf(
                    ReviewInfoEntity(
                        id = 1,
                        placeId = 1,
                        writerId = 1,
                        writerNickName = "User1",
                        reviewScore = 4.5f,
                        reviewContent = "너무 좋아요 너무 좋아요 너무 좋아요 너무 좋아요 너무 좋아요 너무 좋아요너무 좋아요너무 좋아요",
                        createdAt = "2024-01-01",
                    ),
                    ReviewInfoEntity(
                        id = 1,
                        placeId = 1,
                        writerId = 1,
                        writerNickName = "User1",
                        reviewScore = 4.5f,
                        reviewContent = "너무 좋아요",
                        createdAt = "2024-01-01",
                    ),
                    ReviewInfoEntity(
                        id = 1,
                        placeId = 1,
                        writerId = 1,
                        writerNickName = "User1",
                        reviewScore = 4.5f,
                        reviewContent = "너무 좋아요",
                        createdAt = "2024-01-01",
                    ),
                    ReviewInfoEntity(
                        id = 1,
                        placeId = 1,
                        writerId = 1,
                        writerNickName = "User1",
                        reviewScore = 4.5f,
                        reviewContent = "너무 좋아요",
                        createdAt = "2024-01-01",
                    ),
                ),
            onClickWriteReview = {},
            onClickLike = {},
            onClickBookmark = {},
            isLike = false,
            isBookmark = false,
        )
    }
}
