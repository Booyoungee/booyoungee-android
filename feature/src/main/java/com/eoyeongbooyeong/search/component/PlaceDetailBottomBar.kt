package com.eoyeongbooyeong.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.button.BooLargeButton
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
fun PlaceDetailBottomBar(
    onClickLike: () -> Unit,
    onClickBookmark: () -> Unit,
    onClickWriteReview: () -> Unit,
    likeCount: Int,
    bookmarkCount: Int,
    isLike: Boolean,
    isBookmark: Boolean,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(White)
                .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter =
                    if (isLike) {
                        painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_like_24)
                    } else {
                        painterResource(
                            id = com.eoyeongbooyeong.core.R.drawable.ic_like_default_24,
                        )
                    },
                contentDescription = "favorite",
                modifier =
                    Modifier
                        .padding(horizontal = 9.dp, vertical = 4.dp)
                        .noRippleClickable {
                            onClickLike()
                        },
            )
            Text(text = likeCount.toString(), style = BooTheme.typography.caption2, color = Gray400)
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter =
                    if (isBookmark) {
                        painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_bookmark_20)
                    } else {
                        painterResource(
                            id = com.eoyeongbooyeong.core.R.drawable.ic_bookmark_default_24,
                        )
                    },
                contentDescription = "favorite",
                modifier =
                    Modifier
                        .padding(horizontal = 9.dp, vertical = 4.dp)
                        .noRippleClickable {
                            onClickBookmark()
                        },
            )
            Text(
                text = bookmarkCount.toString(),
                style = BooTheme.typography.caption2,
                color = Gray400,
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        BooLargeButton(
            text = "리뷰 작성하기",
            modifier = Modifier.weight(4f),
            onClick = { onClickWriteReview() },
        )
    }
}

@Composable
@Preview
fun PlaceDetailBottomBarPreview() {
    BooTheme {
        PlaceDetailBottomBar(
            onClickLike = {},
            likeCount = 100,
            isLike = false,
            onClickBookmark = {},
            bookmarkCount = 100,
            isBookmark = false,
            onClickWriteReview = {},
        )
    }
}
