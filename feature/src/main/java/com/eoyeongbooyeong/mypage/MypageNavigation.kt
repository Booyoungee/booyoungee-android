package com.eoyeongbooyeong.mypage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.MainTabRoute
import kotlinx.serialization.Serializable

@Serializable
data object Mypage : MainTabRoute

fun NavController.navigateToMypage(navOptions: NavOptions) {
    navigate(Mypage, navOptions)
}

fun NavGraphBuilder.mypageNavGraph() {
    composable<Mypage> {
        MyPageScreen()
    }
}
