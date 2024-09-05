package com.eoyeongbooyeong.new_home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.home.HomeScreen
import com.eoyeongbooyeong.navigation.MainTabRoute
import kotlinx.serialization.Serializable

@Serializable
data object NewHome : MainTabRoute

fun NavController.navigateToNewHome(navOptions: NavOptions) {
    navigate(NewHome, navOptions)
}

fun NavGraphBuilder.newHomeNavGraph(
    // TODO: Add parameters
) {
    composable<NewHome> {
        HomeScreen()
    }
}
