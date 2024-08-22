package com.eoyeongbooyeong.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReviewStar(star: Float) {
    val filledStars = star.toInt()
    val maxStars = 5

    Row {
        repeat(filledStars) {
            Image(
                painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_filled_star),
                contentDescription = "filled star(review) icon",
                modifier = Modifier.size(12.dp),
            )
        }
        repeat(maxStars - filledStars) {
            Image(
                painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_empty_star),
                contentDescription = "empty star (review) icon",
                modifier = Modifier.size(12.dp),
            )
        }
    }
}

@Composable
@Preview
fun ReviewStarPreview() {
    ReviewStar(4.5f)
}
