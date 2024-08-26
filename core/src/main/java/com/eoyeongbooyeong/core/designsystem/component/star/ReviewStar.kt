package com.eoyeongbooyeong.core.designsystem.component.star

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.extension.noRippleClickable

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
fun ClickableReviewStar(
    modifier: Modifier = Modifier,
    onClickReviewScore: (Int) -> Unit
) {
    val maxStar = 5
    val selectedStarIndex = remember { mutableStateOf(0) }

    LazyRow(
        modifier = modifier,
    ) {
        itemsIndexed((1..maxStar).toList()) { index, _ ->

            val isSelected = index < selectedStarIndex.value

            Box(
                modifier =
                    Modifier
                        .noRippleClickable {
                            if (selectedStarIndex.value == index + 1) {
                                selectedStarIndex.value = 0
                            } else {
                                selectedStarIndex.value = index + 1
                            }
                            onClickReviewScore(selectedStarIndex.value)
                        }.padding(4.dp),
            ) {
                Image(
                    painter =
                        painterResource(
                            id =
                                if (isSelected) {
                                    com.eoyeongbooyeong.core.R.drawable.ic_filled_star
                                } else {
                                    com.eoyeongbooyeong.core.R.drawable.ic_empty_star
                                },
                        ),
                    contentDescription =
                        if (isSelected) {
                            "filled star (review) icon"
                        } else {
                            "empty star (review) icon"
                        },
                    modifier = Modifier.size(30.dp),
                )
            }
        }
    }
}

@Composable
@Preview
fun ReviewStarPreview() {
    ReviewStar(4.5f)
}

@Composable
@Preview
fun ClickableReviewStarPreview() {
    BooTheme {
        ClickableReviewStar {}
    }
}
