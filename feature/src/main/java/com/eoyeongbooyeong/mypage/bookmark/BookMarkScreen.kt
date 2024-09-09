package com.eoyeongbooyeong.mypage.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.search.component.PlaceInfoListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BookMarkRoute(
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getBookmarkList()
    }

    BookMarkScreen(
        bookmarkList = state.value.myBookmarkList
    )
}

@Composable
fun BookMarkScreen(
    bookmarkList: ImmutableList<PlaceInfoEntity> = persistentListOf(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ) {
        BooTextTopAppBar(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                    contentDescription = "left",
                    modifier = Modifier.noRippleClickable {
                        // navigate Up
                    })
            },
            text = "북마크"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(bookmarkList) {
                PlaceInfoListItem(
                    placeName = it.name,
                    address = it.address,
                    placeImageUrl = it.images.firstOrNull(),
                    star = it.starCount,
                    reviewCount = it.reviewCount,
                    likedCount = it.likeCount,
                    movieNameList = it.movies,
                    isBookmarked = it.me.hasBookmark
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBookMarkScreen() {
    BooTheme {
        BookMarkScreen()
    }
}
