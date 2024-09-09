package com.eoyeongbooyeong.domain.repository

import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity

interface BookmarkRepository {
    suspend fun getBookmarkList(): Result<List<PlaceInfoEntity>>
}
