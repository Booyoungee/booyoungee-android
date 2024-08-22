package com.eoyeongbooyeong.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray200
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.listToBracketedString
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.domain.Place
import com.eoyeongbooyeong.feature.R

@Composable
internal fun PlaceInfoBox(
    place: Place,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier =
            modifier
                .noRippleClickable(onClick = onClick)
                .border(
                    width = 1.dp,
                    color = Gray200,
                    shape = RoundedCornerShape(10.dp),
                ).clip(RoundedCornerShape(10.dp))
                .background(White)
                .padding(start = 31.dp, top = 25.dp, bottom = 25.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(White)
                    .padding(end = 10.dp),
        ) {
            AsyncImage(
                model = place.imageUrl,
                contentDescription = null,
                modifier =
                    Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(5.dp)),
                placeholder = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_default_5),
                error = painterResource(id = com.eoyeongbooyeong.core.R.drawable.img_default_5),
            )
            Column(
                modifier = Modifier.padding(start = 10.dp),
            ) {
                Text(
                    text = place.name,
                    style = BooTheme.typography.body3,
                    color = Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, false),
                )
                Text(
                    text = place.address,
                    style = BooTheme.typography.caption2,
                    color = Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, false),
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ReviewStar(place.star)

                    Text(
                        text = place.star.toString(),
                        style = BooTheme.typography.caption1,
                        color = Black,
                        modifier = Modifier.weight(1f, false).padding(start = 4.dp),
                    )
                    Text(
                        text = stringResource(R.string.placeReviewAndPoint, place.reviewCount),
                        style = BooTheme.typography.caption2,
                        color = Gray400,
                        modifier =
                            Modifier
                                .weight(1f, false)
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
                        modifier = Modifier.weight(1f, false).padding(start = 4.dp),
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_camera),
                        contentDescription = "camera icon",
                        modifier = Modifier.size(20.dp).padding(end = 8.dp),
                    )
                    Text(
                        text = place.movieNameList.listToBracketedString(),
                        style = BooTheme.typography.caption1,
                        color = Black,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 4.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PlaceInfoBoxPreview() {
    BooTheme {
        PlaceInfoBox(
            place =
                Place(
                    name = "Place Name",
                    address = "Address",
                    star = 4.5f,
                    reviewCount = 100,
                    likedCount = 100,
                    movieNameList = listOf("Movie 1", "Movie 2"),
                ),
        )
    }
}
