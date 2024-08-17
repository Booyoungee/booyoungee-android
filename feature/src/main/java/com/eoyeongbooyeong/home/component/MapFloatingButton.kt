package com.eoyeongbooyeong.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.Gray300
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.feature.R

@Composable
fun MyLocationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column {
        Box(
            modifier = modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.dp, Gray300, CircleShape)
                .background(White)
                .noRippleClickable(onClick = onClick)
        ) {
            Image(
                painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_location),
                contentDescription = "reposition user location"
            )
        }
    }
}