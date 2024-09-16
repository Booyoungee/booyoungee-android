package com.eoyeongbooyeong.places.map

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class KakaoMap(
    val placeType: String
)

fun NavGraphBuilder.kakaoMapNavGraph(
    navigateUp: () -> Unit,
    navigateToPlaceDetail: (Int, String) -> Unit,
) {
    composable<KakaoMap> { backStackEntry ->
        KakaoMapRoute(
            placeType = backStackEntry.arguments?.getString("placeType") ?: "",
            onBackClick = navigateUp,
            onClickPlaceDetail = navigateToPlaceDetail,
        )
    }
}