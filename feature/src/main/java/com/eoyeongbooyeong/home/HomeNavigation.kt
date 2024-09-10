package com.eoyeongbooyeong.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.MainTabRoute
import kotlinx.serialization.Serializable

@Serializable
data object Home : MainTabRoute

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
    navigateToSearch: () -> Unit
) {
    composable<Home> {
        HomeRoute(
            paddingValues = paddingValues,
            navigateToSearch = navigateToSearch
        )
    }
}
