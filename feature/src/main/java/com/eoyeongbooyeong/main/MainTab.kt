package com.eoyeongbooyeong.main

import androidx.compose.runtime.Composable
import com.eoyeongbooyeong.home.Home
import com.eoyeongbooyeong.mypage.MyPage
import com.eoyeongbooyeong.navigation.MainTabRoute
import com.eoyeongbooyeong.navigation.Route
import com.eoyeongbooyeong.place_recommend.Place
import com.eoyeongbooyeong.stamp.Stamp

internal enum class MainTab(
    val defaultIconResource: Int,
    internal val contentDescription: String,
    val route: MainTabRoute,
    val showBottomSheet: Boolean = true,
) {
    HOME(
        defaultIconResource = com.eoyeongbooyeong.core.R.drawable.ic_home_default,
        contentDescription = "홈",
        route = Home,
    ),
    PLACE(
        defaultIconResource = com.eoyeongbooyeong.core.R.drawable.ic_place_default,
        contentDescription = "장소 추천",
        route = Place,
    ),
    STAMP(
        defaultIconResource = com.eoyeongbooyeong.core.R.drawable.ic_stamp_default,
        contentDescription = "스탬프",
        route = Stamp,
    ),
    MYPAGE(
        defaultIconResource = com.eoyeongbooyeong.core.R.drawable.ic_mypage_default,
        contentDescription = "마이페이지",
        route = MyPage,
    ),
    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? = entries.find { predicate(it.route) }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean = entries.map { it.route }.any { predicate(it) }
    }
}
