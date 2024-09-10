package com.eoyeongbooyeong.mypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.eoyeongbooyeong.mypage.bookmark.BookmarkRoute
import com.eoyeongbooyeong.mypage.edit_nickname.EditNicknameRoute
import com.eoyeongbooyeong.mypage.review.MyReviewRoute
import com.eoyeongbooyeong.navigation.MainTabRoute
import com.eoyeongbooyeong.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object MyPage : MainTabRoute

@Serializable
data object MyPageEditNickname : Route

@Serializable
data object MyReview : Route

@Serializable
data object MyBookMark : Route

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MyPage, navOptions)
}

fun NavGraphBuilder.myPageNavGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToEditNickname: () -> Unit,
    navigateToMyReview: () -> Unit,
    navigateToBookmark: () -> Unit,
) {
    composable<MyPage> {
        MyPageRoute(
            paddingValues,
            navigateToEditNickname,
            navigateToMyReview,
            navigateToBookmark
        )
    }
    composable<MyPageEditNickname> {
        EditNicknameRoute(
            navigateUp = navigateUp
        )
    }

    composable<MyReview> {
        MyReviewRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp
        )
    }

    composable<MyBookMark> {
        BookmarkRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp
        )
    }
}
