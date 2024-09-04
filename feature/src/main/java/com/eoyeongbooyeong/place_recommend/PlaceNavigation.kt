package com.eoyeongbooyeong.place_recommend

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.MainTabRoute
import kotlinx.serialization.Serializable

@Serializable
data object Place : MainTabRoute

fun NavController.navigateToPlace(navOptions: NavOptions) {
    navigate(Place, navOptions)
}

fun NavGraphBuilder.placeNavGraph() {
    composable<Place> {
        PlaceRecommend()
    }
}
