package com.eoyeongbooyeong.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object Splash : Route

fun NavGraphBuilder.splashNavGraph(
    navigateToHome: () -> Unit,
    navigateToLogIn: () -> Unit,
) {
    composable<Splash> {
        SplashRoute(
            navigateToHome = navigateToHome,
            navigateToLogIn = navigateToLogIn,
        )
    }
}
