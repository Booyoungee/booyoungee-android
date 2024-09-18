package com.eoyeongbooyeong.stamp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme

@Composable
fun StampItem(
    imageUrl: String?,
    text: String,
    modifier: Modifier = Modifier,
    isLocked: Boolean = false,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (!isLocked) {
                    onClick()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            AsyncImage(
                model = imageUrl ?: com.eoyeongbooyeong.core.R.drawable.img_default_5,
                contentDescription = "스템프 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f)
                    .clip(CircleShape),
            )

            if (isLocked) {
                Image(
                    painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_stamp_boo),
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Black.copy(alpha = 0.5f))
                        .fillMaxWidth(0.8f)
                        .padding(8.dp)
                        .aspectRatio(1f),
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            style = BooTheme.typography.caption1,
            color = Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StampItemPreview() {
    BooTheme {
        StampItem(
            imageUrl = null,
            text = "아홉산 숲"
        )
    }
}
