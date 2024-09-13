package com.eoyeongbooyeong.places.category

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CategoryPlace(
    val placeType: String,
)

fun NavController.navigateToCategoryPlace(navOptions: NavOptions, placeType: String) {
    navigate(CategoryPlace(placeType), navOptions)
}

fun NavGraphBuilder.categoryPlaceNavGraph(
    navigateUp: () -> Unit,
) {
    composable<CategoryPlace> { backStackEntry ->
        PlaceCategoryRoute(
            placeType = backStackEntry.arguments?.getString("placeType") ?: "",
            onBackClick = navigateUp,
        )
    }
}