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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.button.BooLargeButton
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.Red
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun ReviewScreen(
    onValueChange: (String) -> Unit,
    isWarning: Boolean,
    value: String,
) {
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .background(color = White),
    ) {
        Spacer(modifier = Modifier.padding(top = 10.dp))
        // TODO: Tob Bar

        Spacer(
            modifier =
            Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
        )
        Text(
            text = "방문 후기를 알려주세요!",
            style = BooTheme.typography.head3,
            color = Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        // TODO 별점 UI
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
            value = value,
            onValueChange = onValueChange,
            modifier =
            Modifier
                .sizeIn(minHeight = 310.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp, start = 24.dp, end = 24.dp)
                .background(color = Gray100, shape = RoundedCornerShape(10.dp)),
            textStyle = BooTheme.typography.body4,
            decorationBox = { innerTextField ->
                Box(
                    modifier =
                    Modifier
                        .padding(horizontal = 10.dp, vertical = 16.dp)
                        .weight(1f),
                ) {
                    innerTextField()
                    if (value.isEmpty()) {
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
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            )
        }


        Spacer(modifier = Modifier.weight(5f))
        Box(contentAlignment = Alignment.BottomCenter) {
            BooLargeButton(
                text = "등록하기",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview
fun ReviewScreenPreview() {
    BooTheme {
        ReviewScreen(
            onValueChange = {},
            isWarning = true,
            value = "",
        )
    }
}
