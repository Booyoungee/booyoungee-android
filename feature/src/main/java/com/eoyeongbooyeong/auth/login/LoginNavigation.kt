package com.eoyeongbooyeong.auth.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object Login : Route

fun NavGraphBuilder.loginNavGraph(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    composable<Login> {
        LoginRoute(
            navigateToHome = navigateToHome,
            navigateToSignUp = navigateToSignUp,
        )
    }
}
