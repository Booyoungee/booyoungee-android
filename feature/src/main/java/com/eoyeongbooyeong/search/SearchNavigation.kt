package com.eoyeongbooyeong.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.MainTabRoute
import com.eoyeongbooyeong.navigation.Route
import com.eoyeongbooyeong.place_recommend.Place
import com.eoyeongbooyeong.place_recommend.PlaceRecommend
import kotlinx.serialization.Serializable

@Serializable
data object Search : Route

fun NavController.navigateToSearch(navOptions: NavOptions) {
    navigate(Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph() {
    composable<Search> {
        SearchRoute()
    }
}
