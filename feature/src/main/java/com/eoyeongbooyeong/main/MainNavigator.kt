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
import com.eoyeongbooyeong.home.Home
import com.eoyeongbooyeong.home.navigateToHome
import com.eoyeongbooyeong.mypage.navigateToMypage
import com.eoyeongbooyeong.navigation.Route
import com.eoyeongbooyeong.place_recommend.navigateToPlace
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
        when (tab) {
            MainTab.HOME ->
                navController.navigateToHome(
                    navOptions =
                        navOptions {
                            popUpTo<Home> {
                                inclusive = false
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        },
                )

            MainTab.PLACE ->
                navController.navigateToPlace(
                    // TODO : navOptions 수정
                    navOptions {
                        popUpTo<Home> {
                            inclusive = false
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = false
                    },
                )

            MainTab.STAMP ->
                navController.navigateToStamp(
                    // TODO : navOptions 수정
                    navOptions {
                        popUpTo<Home> {
                            inclusive = false
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = false
                    },
                )

            MainTab.MYPAGE ->
                navController.navigateToMypage(
                    // TODO : navOptions 수정
                    navOptions {
                        popUpTo<Home> {
                            inclusive = false
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = false
                    },
                )
        }
    }

    private fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToHome(navOptions: NavOptions) {
        navController.navigate(Home, navOptions)
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean = navController.currentDestination?.hasRoute<T>() == true

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
