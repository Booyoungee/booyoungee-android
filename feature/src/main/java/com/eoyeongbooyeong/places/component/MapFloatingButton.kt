package com.eoyeongbooyeong.places.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.eoyeongbooyeong.core.designsystem.theme.Blue400
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White

data class FloatingButton(
    val isMyLocationButton: Boolean = false,
    val isBookmarkButton: Boolean = false,
    val navigateToMap: Boolean = false,
)

@Composable
internal fun MapFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    buttonState: FloatingButton,
) {
    Column {
        Box(
            modifier = modifier.size(50.dp).clip(CircleShape).clickable(onClick = onClick),
        ) {
            when {
                buttonState.isMyLocationButton ->
                    Image(
                        painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_location),
                        contentDescription = "reposition user location",
                    )

                buttonState.isBookmarkButton ->
                    Image(
                        painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_bookmark_checked),
                        contentDescription = "bookmark location",
                    )
            }
        }
    }
}

@Composable
fun NavigateToMapFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .clip(RoundedCornerShape(30.dp))
            .background(Blue400)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_map_white),
                contentDescription = "Navigate to map",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "지도 보기",
                style = BooTheme.typography.body3,
                color = White,
                modifier = Modifier.padding(vertical = 6.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@Composable
fun FloatingButtonContainer(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(end = 16.dp, bottom = 56.dp).zIndex(1f)
    ) {
        NavigateToMapFloatingButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1f),
            onClick = onClick
        )
    }
}

@Composable
@Preview
fun MapFloatingButtonPreview() {
    BooTheme {
        Column {
            MapFloatingButton(
                buttonState = FloatingButton(isMyLocationButton = true),
            )
            MapFloatingButton(
                buttonState = FloatingButton(isBookmarkButton = true),
            )
            NavigateToMapFloatingButton()
        }
    }
}
