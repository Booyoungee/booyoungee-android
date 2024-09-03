package com.eoyeongbooyeong.mypage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.MainTabRoute
import kotlinx.serialization.Serializable

@Serializable
data object MyPage : MainTabRoute

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MyPage, navOptions)
}

fun NavGraphBuilder.myPageNavGraph() {
    composable<MyPage> {
        MyPageRoute()
    }
}
