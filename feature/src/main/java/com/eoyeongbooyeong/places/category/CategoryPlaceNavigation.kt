package com.eoyeongbooyeong.places.category

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CategoryPlace(
    val placeType: String,
)

fun NavGraphBuilder.categoryPlaceNavGraph(
    navigateUp: () -> Unit,
    navigateToPlaceDetail: (Int, String) -> Unit,
    navigateToKakaoMap: (String) -> Unit,
) {
    composable<CategoryPlace> { backStackEntry ->
        PlaceCategoryRoute(
            placeType = backStackEntry.arguments?.getString("placeType") ?: "",
            onBackClick = navigateUp,
            navigateToPlaceDetail = navigateToPlaceDetail,
            navigateToKakaoMap = navigateToKakaoMap,
        )
    }
}
