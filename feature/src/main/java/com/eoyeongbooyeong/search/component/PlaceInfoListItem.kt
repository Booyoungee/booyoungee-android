package com.eoyeongbooyeong.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.eoyeongbooyeong.core.designsystem.component.star.ReviewStar
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.Blue400
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.listToBracketedString
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.feature.R

@Composable
internal fun PlaceInfoListItem(
    placeName: String,
    address: String,
    star: Double,
    reviewCount: Int,
    likedCount: Int,
    movieNameList: List<String>?,
    placeImageUrl: String?,
    modifier: Modifier = Modifier,
    isBookmarked: Boolean = false,
    onClick: () -> Unit = {},
    onBookMarkClick: () -> Unit = {},
) {
    Box(
        modifier =
        modifier
            .noRippleClickable(onClick = onClick)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(White),
    ) {
        Row(
            modifier =
            modifier
                .wrapContentSize()
                .height(IntrinsicSize.Min)
                .padding(end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = placeImageUrl ?: com.eoyeongbooyeong.core.R.drawable.img_default_5,
                contentDescription = null,
                modifier =
                modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(5.dp)),
                placeholder = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_default_5),
                error = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_default_5),
            )

            Spacer(modifier = modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = placeName,
                        style = BooTheme.typography.body3,
                        color = Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, false)
                    )

                    Image(
                        imageVector = ImageVector.vectorResource(
                            id = if (isBookmarked) {
                                com.eoyeongbooyeong.core.R.drawable.ic_bookmark_20
                            } else {
                                com.eoyeongbooyeong.core.R.drawable.ic_bookmark_empty_20
                            }
                        ),
                        contentDescription = "bookmark icon",
                        modifier = Modifier.size(20.dp).noRippleClickable(onClick = onBookMarkClick),
                        colorFilter = ColorFilter.tint(Blue400)
                    )
                }


                Text(
                    text = address,
                    style = BooTheme.typography.caption2,
                    color = Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    ReviewStar(star)

                    Text(
                        text = star.toString(),
                        style = BooTheme.typography.caption1,
                        color = Black,
                        modifier =
                        modifier
                            .weight(1f, false)
                            .padding(start = 4.dp),
                    )
                    Text(
                        text = stringResource(R.string.placeReviewAndPoint, reviewCount),
                        style = BooTheme.typography.caption2,
                        color = Gray400,
                        modifier =
                        modifier
                            .weight(1f, false)
                            .padding(start = 4.dp),
                    )
                    Image(
                        painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_like),
                        contentDescription = "liked icon",
                        modifier = Modifier.size(12.dp),
                    )
                    Text(
                        text = likedCount.toString(),
                        style = BooTheme.typography.caption1,
                        color = Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier =
                        modifier
                            .weight(1f, false)
                            .padding(start = 4.dp),
                    )
                }
                if (movieNameList != null) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_film),
                            contentDescription = "camera icon",
                            modifier = modifier.size(20.dp),
                        )
                        Text(
                            text = movieNameList.listToBracketedString(),
                            style = BooTheme.typography.caption1,
                            color = Black,
                            maxLines = 1,
                            modifier =
                            modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
            Spacer(modifier = modifier.width(8.dp))
        }
    }
}

@Composable
fun PlaceReviewAndLikedCount(
    modifier: Modifier = Modifier,
    star: Double = 0.0,
    reviewCount: Int = 0,
    likedCount: Int = 0,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ReviewStar(star)

        Text(
            text = star.toString(),
            style = BooTheme.typography.caption1,
            color = Black,
            modifier = Modifier
                .weight(1f, false)
                .padding(start = 4.dp),
        )
        Text(
            text = stringResource(R.string.placeReviewAndPoint, reviewCount),
            style = BooTheme.typography.caption2,
            color = Gray400,
            modifier = Modifier
                .weight(1f, false)
                .padding(start = 4.dp),
        )
        Image(
            painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_like),
            contentDescription = "liked icon",
            modifier = Modifier.size(12.dp),
        )
        Text(
            text = likedCount.toString(),
            style = BooTheme.typography.caption1,
            color = Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f, false)
                .padding(start = 4.dp),
        )
    }
}

@Preview
@Composable
fun PlaceInfoResultListPreview() {
    BooTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            PlaceInfoListItem(
                placeName = "PlaceEntity Name",
                placeImageUrl = "https://placeimg.com/100/100/any",
                address = "Address",
                star = 4.5,
                reviewCount = 100,
                likedCount = 100,
                isBookmarked = false,
                movieNameList = listOf("Movie 1", "Movie 2"),
                modifier = Modifier.background(White),
            )
        }

    }
}
