package com.eoyeongbooyeong.core.designsystem.component.star

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.component.menu.ReviewDropdownMenu
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun Review(
    modifier: Modifier = Modifier,
    reviewId: Int = -1,
    writerId: Int = -1,
    nickName: String = "",
    reviewScore: Double = 0.0,
    reviewContent: String = "",
    reviewDate: String = "",
    onReportClick: (Int) -> Unit,
    onBlockClick: (Int) -> Unit
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile_24),
                    contentDescription = "default profile",
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = nickName, style = BooTheme.typography.caption1, color = Black)
                    ReviewStar(star = reviewScore)
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
            ) {
                val menuItems = listOf("신고하기", "차단하기")
                ReviewDropdownMenu(menuItems) { selectedItem ->
                    when (selectedItem) {
                        "신고하기" -> onReportClick(reviewId)
                        "차단하기" -> onBlockClick(writerId)
                    }
                }
            }
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
fun ReviewPreview() {
    BooTheme {
        Review(
            nickName = "김보영",
            reviewScore = 4.5,
            reviewContent = "너무 좋아요",
            reviewDate = "2021.10.10",
            modifier = Modifier.background(White),
            onReportClick = {},
            onBlockClick = {},
        )
    }
}
