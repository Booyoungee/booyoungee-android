package com.eoyeongbooyeong.core.designsystem.component.star

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun MyReviewComponent(
    modifier: Modifier = Modifier,
    reviewId: Int = -1,
    writerId: Int = -1,
    storeName: String = "",
    reviewScore: Double = 0.0,
    reviewContent: String = "",
    reviewDate: String = "",
) {
    Column(
        modifier = modifier.background(White),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .background(White),
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = storeName, style = BooTheme.typography.body3, color = Black)
                    Spacer(modifier = Modifier.width(2.dp))
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_right),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        colorFilter = ColorFilter.tint(Black)
                    )
                }

                ReviewStar(star = reviewScore)
            }

            Text(text = reviewDate, style = BooTheme.typography.caption4, color = Black)
        }

        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = reviewContent,
            style = BooTheme.typography.body4,
            color = Black,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
@Preview
fun MyReviewPreview() {
    BooTheme {
        MyReviewComponent(
            storeName = "아홉산 숲",
            reviewScore = 4.5,
            reviewContent = "생각보다 내부가 엄청 넓었어요. 천천히 산책하면서 사진찍으려면 1시간30분~2시간은 잡아야 가능할 곳이에요.",
            reviewDate = "2021.10.10",
            modifier = Modifier.background(White),
        )
    }
}
