package com.eoyeongbooyeong.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.MainTabRoute
import kotlinx.serialization.Serializable

@Serializable
data object Splash : MainTabRoute

fun NavGraphBuilder.splashNavGraph(
    navigateToHome: (Boolean) -> Unit,
    navigateToLogIn: () -> Unit,
) {
    composable<Splash> {
        SplashScreen(
            navigateToHome = navigateToHome,
            navigateToLogIn = navigateToLogIn,
        )
    }
}
