package com.eoyeongbooyeong.auth.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.auth.login.Login
import com.eoyeongbooyeong.auth.login.LoginRoute
import com.eoyeongbooyeong.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object SignUp : Route

fun NavGraphBuilder.signUpNavGraph(
    navigateToHome: () -> Unit,
) {
    composable<SignUp> {
        SignUpRoute(
            navigateToHome = navigateToHome
        )
    }
}
