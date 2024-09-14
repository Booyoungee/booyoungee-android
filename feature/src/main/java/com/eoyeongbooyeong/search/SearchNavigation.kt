package com.eoyeongbooyeong.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object Search : Route

fun NavController.navigateToSearch(navOptions: NavOptions) {
    navigate(Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    navigateUp: () -> Unit,
    navigateToPlaceDetail: (Int, String) -> Unit,
) {
    composable<Search> {
        SearchRoute(
            navigateUp = navigateUp,
            navigateToPlaceDetail = navigateToPlaceDetail,
        )
    }
}
