package com.eoyeongbooyeong.mypage.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.search.component.PlaceInfoListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BookmarkRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is BookmarkSideEffect.NavigateUp -> navigateUp()
                }
            }
    }

    LaunchedEffect(Unit) {
        viewModel.getBookmarkList()
    }

    BookmarkScreen(
        paddingValues = paddingValues,
        bookmarkList = state.value.myBookmarkList,
        navigateUp = viewModel::navigateUp
    )
}

@Composable
fun BookmarkScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    bookmarkList: ImmutableList<PlaceInfoEntity> = persistentListOf(),
    navigateUp: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(White),
    ) {
        BooTextTopAppBar(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                    contentDescription = "left",
                    modifier = Modifier.noRippleClickable(navigateUp)
                )
            },
            text = "북마크"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(bookmarkList) {
                PlaceInfoListItem(
                    placeName = it.name,
                    address = it.address,
                    placeImageUrl = it.images.firstOrNull(),
                    star = it.stars,
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
fun PreviewBookMarkScreen2() {
    BooTheme {
        BookmarkScreen()
    }
}
