package com.eoyeongbooyeong.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
fun BookMarkRoute() {
    BookMarkScreen()
}

@Composable
fun BookMarkScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ) {
        BooTextTopAppBar(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                    contentDescription = "left",
                    modifier = Modifier.noRippleClickable {
                        // navigate Up
                    })
            },
            text = "북마크"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) { // PlaceInfoListItem 적용 예정
            items(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) {
                Text(
                    text = "북마크 아이템 $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBookMarkScreen() {
    BooTheme {
        BookMarkScreen()
    }
}
