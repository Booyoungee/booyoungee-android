package com.eoyeongbooyeong.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
internal fun MyPageRoute() {
    MyPageScreen()
}

@Composable
internal fun MyPageScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        BooTextTopAppBar(text = "마이페이지")

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_profile),
                    contentDescription = "profile Image"
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    Text(text = "닉네임")
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_right),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Gray100)
                        .padding(vertical = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_write),
                        contentDescription = "my Review"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "내 리뷰",
                        style = BooTheme.typography.caption1
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Gray100)
                        .padding(vertical = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_bookmark),
                        contentDescription = "my Review"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "북마크",
                        style = BooTheme.typography.caption1
                    )

                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            MyPageOptionItem(
                text = "서비스 이용약관"
            ) {

            }
            MyPageOptionItem(
                text = "개인정보 처리방침"
            ) {

            }
            MyPageOptionItem(
                text = "로그아웃"
            ) {

            }
            MyPageOptionItem(
                text = "탈퇴하기"
            ) {

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "앱 버전",
                    color = Gray400,
                    style = BooTheme.typography.caption2
                )
                Text(
                    text = "1.0.0",
                    color = Gray400,
                    style = BooTheme.typography.caption2
                )
            }
        }
    }
}

@Composable
fun MyPageOptionItem(
    text: String,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = BooTheme.typography.body4
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_right),
            contentDescription = "right arrow"
        )
    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    BooTheme {
        MyPageScreen()
    }
}
