package com.eoyeongbooyeong.stamp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.MainTabRoute
import kotlinx.serialization.Serializable

@Serializable
data object Stamp : MainTabRoute

fun NavController.navigateToStamp(navOptions: NavOptions) {
    navigate(Stamp, navOptions)
}

fun NavGraphBuilder.stampNavGraph() {
    composable<Stamp> {
        StampScreen()
    }
}
