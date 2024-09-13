package com.eoyeongbooyeong.places.details

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.search.PlaceDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDetail(
    val placeId: Int,
    val placeType: String = "movie",
)

fun NavGraphBuilder.placeDetailNavGraph(
    navigateUp: () -> Unit,
    navigateToWriteReview: (Int) -> Unit,
) {
    composable<PlaceDetail> { backStackEntry ->
        PlaceDetailRoute(
            placeId = backStackEntry.arguments?.getInt("placeId") ?: 0,
            placeType = backStackEntry.arguments?.getString("placeType") ?: "",
            onClickBackButton = navigateUp,
            onClickWriteReview = navigateToWriteReview,
        )
    }
}
