package com.eoyeongbooyeong.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun ReviewStar(star: Float) {
    val filledStars = star.toInt()
    val maxStars = 5

    Row {
        repeat(filledStars) {
            Image(
                painter =
                    rememberAsyncImagePainter(
                        model =
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(com.eoyeongbooyeong.core.R.drawable.ic_filled_star)
                                .decoderFactory(SvgDecoder.Factory())
                                .build(),
                    ),
                contentDescription = "",
                modifier = Modifier.size(12.dp),
            )
        }
        repeat(maxStars - filledStars) {
            Image(
                painter =
                    rememberAsyncImagePainter(
                        model =
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(com.eoyeongbooyeong.core.R.drawable.ic_empty_star)
                                .decoderFactory(SvgDecoder.Factory())
                                .build(),
                    ),
                contentDescription = "",
                modifier = Modifier.size(12.dp),
            )
        }
    }
}
