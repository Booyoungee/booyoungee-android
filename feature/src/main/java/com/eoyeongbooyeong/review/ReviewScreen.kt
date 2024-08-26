package com.eoyeongbooyeong.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun ReviewRoute(
    onValueChange: (String) -> Unit,
    onReviewSubmitClick: () -> Unit,
    isWarning: Boolean,
    reviewText: String,
) {
    ReviewScreen(
        onValueChange = onValueChange,
        isWarning = isWarning,
        reviewText = reviewText,
        onReviewSubmitClick = onReviewSubmitClick,
    )
}

@Composable
fun ReviewScreen(
    onValueChange: (String) -> Unit,
    onReviewSubmitClick: () -> Unit,
    isWarning: Boolean,
    reviewText: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
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
                },
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "별점을 선택해주세요.",
                style = BooTheme.typography.caption2,
                color = if (isWarning) Red else Gray400,
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

            if (isWarning) {
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
            isWarning = true,
            reviewText = "ㅇㅇㅇㅇㅇㅇㅇㅇ",
            onReviewSubmitClick = {},
        )
    }
}
