package com.eoyeongbooyeong.category.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class FloatingButton(
    val isMyLocationButton: Boolean = false,
    val isBookmarkButton: Boolean = false,
)

@Composable
internal fun MapFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    buttonState: FloatingButton,
) {
    Column {
        Box(
            modifier =
                modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onClick),
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