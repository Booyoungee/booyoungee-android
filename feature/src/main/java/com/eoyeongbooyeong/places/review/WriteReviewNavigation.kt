package com.eoyeongbooyeong.places.review

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class WriteReview(
    val placeId: Int,
    val type: String,
)

fun NavGraphBuilder.writeReviewNavGraph(
    navigateUp: () -> Unit,
) {
    composable<WriteReview> { backStackEntry ->
        ReviewRoute(
            placeId = backStackEntry.arguments?.getInt("placeId") ?: 0,
            type = backStackEntry.arguments?.getString("type") ?: "",
            onBackClick = navigateUp,
            finishWritingReview = navigateUp,
        )
    }
}