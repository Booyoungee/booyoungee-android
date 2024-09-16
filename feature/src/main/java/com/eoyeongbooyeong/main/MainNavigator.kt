package com.eoyeongbooyeong.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.eoyeongbooyeong.auth.login.Login
import com.eoyeongbooyeong.auth.signup.SignUp
import com.eoyeongbooyeong.home.Home
import com.eoyeongbooyeong.home.navigateToHome
import com.eoyeongbooyeong.mypage.MyBookMark
import com.eoyeongbooyeong.mypage.MyPageEditNickname
import com.eoyeongbooyeong.mypage.MyReview
import com.eoyeongbooyeong.mypage.navigateToMyPage
import com.eoyeongbooyeong.navigation.Route
import com.eoyeongbooyeong.places.category.CategoryPlace
import com.eoyeongbooyeong.places.details.PlaceDetail
import com.eoyeongbooyeong.places.map.KakaoMap
import com.eoyeongbooyeong.places.review.WriteReview
import com.eoyeongbooyeong.search.navigateToSearch
import com.eoyeongbooyeong.splash.Splash
import com.eoyeongbooyeong.stamp.navigateToStamp

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = Splash

    val currentTab: MainTab?
        @Composable get() =
            MainTab.find { tab ->
                currentDestination?.hasRoute(tab::class) == true
            }

    fun navigate(tab: MainTab) {
        val navOptions =
            navOptions {
                popUpTo(Home) {
                    inclusive = false
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions = navOptions)

            MainTab.STAMP -> navController.navigateToStamp(navOptions = navOptions)

            MainTab.MYPAGE -> navController.navigateToMyPage(navOptions = navOptions)
        }
    }

    private fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateUpIfNotHome() {
        if (!isSameCurrentDestination<Home>()) {
            navigateUp()
        }
    }

    fun navigateToLogin(navOptions: NavOptions) {
        navController.navigate(Login, navOptions)
    }

    fun navigateToSignUp(navOptions: NavOptions) {
        navController.navigate(SignUp, navOptions)
    }

    fun navigateToHome(navOptions: NavOptions) {
        navController.navigate(Home, navOptions)
    }

    fun navigateToSearch(navOptions: NavOptions) {
        navController.navigateToSearch(navOptions)
    }

    fun navigateToMyPageEditNickname() {
        navController.navigate(MyPageEditNickname)
    }

    fun navigateToMyReview() {
        navController.navigate(MyReview)
    }

    fun navigateToBookmark() {
        navController.navigate(MyBookMark)
    }

    fun navigateToCategoryPlace(navOptions: NavOptions, placeType: String) {
        navController.navigate(CategoryPlace(placeType), navOptions)
    }

    fun navigateToPlaceDetail(navOption: NavOptions, placeId: Int, placeType: String) {
        navController.navigate(PlaceDetail(placeId, placeType), navOption)
    }

    fun navigateToWriteReview(navOption: NavOptions, placeId: Int, type: String) {
        navController.navigate(WriteReview(placeId, type), navOption)
    }

    fun navigateToKakaoMap(navOption: NavOptions, placeType: String) {
        navController.navigate(KakaoMap(placeType), navOption)
    }

    fun navigateUpAndClearStackToHome() {
        navController.popBackStack(Home, inclusive = false)
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean =
        navController.currentDestination?.hasRoute<T>() == true

    @Composable
    fun shouldShowBottomBar() =
        MainTab.contains {
            currentDestination?.hasRoute(it::class) == true
        } &&
                (currentTab?.showBottomSheet ?: true)
}

// MainNavigator 객체 생성, composition 상태 저장
@Composable
internal fun rememberMainNavigator(navController: NavHostController = rememberNavController()): MainNavigator =
    remember(navController) {
        MainNavigator(navController)
    }
