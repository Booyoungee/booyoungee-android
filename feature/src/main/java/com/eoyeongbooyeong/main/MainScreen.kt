package com.eoyeongbooyeong.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.eoyeongbooyeong.auth.login.Login
import com.eoyeongbooyeong.auth.login.loginNavGraph
import com.eoyeongbooyeong.auth.signup.SignUp
import com.eoyeongbooyeong.auth.signup.signUpNavGraph
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.home.homeNavGraph
import com.eoyeongbooyeong.mypage.myPageNavGraph
import com.eoyeongbooyeong.place_recommend.placeNavGraph
import com.eoyeongbooyeong.places.category.categoryPlaceNavGraph
import com.eoyeongbooyeong.places.details.placeDetailNavGraph
import com.eoyeongbooyeong.places.map.kakaoMapNavGraph
import com.eoyeongbooyeong.places.review.writeReviewNavGraph
import com.eoyeongbooyeong.search.searchNavGraph
import com.eoyeongbooyeong.splash.Splash
import com.eoyeongbooyeong.splash.splashNavGraph
import com.eoyeongbooyeong.stamp.stampNavGraph
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
        content = { paddingValue ->
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                ) {
                    splashNavGraph(
                        navigateToHome = {
                            val navOptions =
                                navOptions {
                                    popUpTo<Splash> {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            navigator.navigateToHome(navOptions = navOptions)
                        },
                        navigateToLogIn = {
                            val navOptions =
                                navOptions {
                                    popUpTo<Splash> {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            navigator.navigateToLogin(navOptions = navOptions)
                        },
                    )
                    loginNavGraph(
                        navigateToHome = {
                            val navOptions =
                                navOptions {
                                    popUpTo<Login> {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            navigator.navigateToHome(navOptions = navOptions)
                        },
                        navigateToSignUp = {
                            val navOptions =
                                navOptions {
                                    launchSingleTop = true
                                }
                            navigator.navigateToSignUp(navOptions = navOptions)
                        }
                    )
                    signUpNavGraph(
                        navigateToHome = {
                            val navOptions =
                                navOptions {
                                    popUpTo<SignUp> {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            navigator.navigateToHome(navOptions = navOptions)
                        },
                    )
                    homeNavGraph(
                        paddingValues = paddingValue,
                        navigateToSearch = {
                            navigator.navigateToSearch(navOptions = navOptions {
                                launchSingleTop = true
                            })
                        },
                        navigateToPlaceDetail = { placeId, placeType ->
                            navigator.navigateToPlaceDetail(
                                navOptions { launchSingleTop = true },
                                placeId = placeId,
                                placeType = placeType
                            )
                        },
                        navigateToCategoryPlace = {
                            navigator.navigateToCategoryPlace(navOptions = navOptions {
                                launchSingleTop = true
                            }, placeType = it)
                        },
                    )
                    categoryPlaceNavGraph(
                        navigateUp = navigator::navigateUpIfNotHome,
                        navigateToPlaceDetail = { placeId, placeType ->
                            navigator.navigateToPlaceDetail(
                                navOptions { launchSingleTop = true },
                                placeId = placeId,
                                placeType = placeType
                            )
                        },
                        navigateToKakaoMap = { placeType ->
                            navigator.navigateToKakaoMap(
                                navOptions { launchSingleTop = true },
                                placeType
                            )
                        },
                    )
                    searchNavGraph(
                        navigateUp = navigator::navigateUpIfNotHome,
                        navigateToPlaceDetail = { placeId, placeType ->
                            navigator.navigateToPlaceDetail(
                                navOptions { launchSingleTop = true },
                                placeId = placeId,
                                placeType = placeType
                            )
                        },
                    )
                    placeNavGraph()
                    placeDetailNavGraph(
                        navigateUp = navigator::navigateUpIfNotHome,
                        navigateToWriteReview = { placeId, type ->
                            navigator.navigateToWriteReview(
                                navOptions { launchSingleTop = true },
                                placeId = placeId,
                                type = type
                            )
                        },
                    )

                    kakaoMapNavGraph(
                        navigateUp = navigator::navigateUpIfNotHome,
                        navigateToPlaceDetail = { placeId, placeType ->
                            navigator.navigateToPlaceDetail(
                                navOptions { launchSingleTop = true },
                                placeId = placeId,
                                placeType = placeType
                            )
                        },
                    )

                    writeReviewNavGraph(
                        navigateUp = navigator::navigateUpIfNotHome,
                    )

                    stampNavGraph(
                        paddingValues = paddingValue,
                        navigateToPlaceDetail = { placeId, placeType ->
                            navigator.navigateToPlaceDetail(
                                navOptions { launchSingleTop = true },
                                placeId = placeId,
                                placeType = placeType
                            )
                        },
                    )
                    myPageNavGraph(
                        paddingValues = paddingValue,
                        navigateUp = navigator::navigateUpIfNotHome,
                        navigateToEditNickname = navigator::navigateToMyPageEditNickname,
                        navigateToMyReview = navigator::navigateToMyReview,
                        navigateToBookmark = navigator::navigateToBookmark,
                        navigateToPlaceDetail = { placeId, placeType ->
                            navigator.navigateToPlaceDetail(
                                navOptions { launchSingleTop = true },
                                placeId = placeId,
                                placeType = placeType
                            )
                        },
                    )
                }
            }
        },
        bottomBar = {
            MainBottomBar(
                isVisible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toPersistentList(),
                currentTab = navigator.currentTab ?: MainTab.HOME,
                onTabSelected = navigator::navigate,
            )
        },
    )
}

@Composable
private fun MainBottomBar(
    isVisible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(visible = isVisible, enter = fadeIn(), exit = fadeOut()) {
        Column(
            modifier = Modifier.background(color = White),
        ) {
            HorizontalDivider(color = Gray100)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .navigationBarsPadding()
                    .height(86.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                tabs.forEach { tab ->
                    MainBottomBarItem(
                        tab = tab,
                        isSelected = (tab == currentTab),
                        onClick = { onTabSelected(tab) },
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainTab,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier =
        Modifier
            .fillMaxHeight()
            .selectable(
                selected = isSelected,
                indication = null,
                role = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            )
            .weight(1f),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Icon(
                painter = painterResource(id = tab.defaultIconResource),
                contentDescription = tab.contentDescription,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally),
                tint = if (isSelected) Black else Gray400,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = tab.contentDescription,
                style = BooTheme.typography.caption1,
                color = if (isSelected) Black else Gray400,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
            )
        }
    }
}
