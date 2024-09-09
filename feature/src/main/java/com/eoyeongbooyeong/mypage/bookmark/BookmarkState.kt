package com.eoyeongbooyeong.mypage.bookmark

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class BookmarkState(
    val myBookmarkList: ImmutableList<PlaceInfoEntity> = persistentListOf(),
)
