package com.eoyeongbooyeong.data.datasource

import com.eoyeongbooyeong.data.dto.response.PlaceDto

interface BookmarkDataSource {
    suspend fun getBookmarkList(): List<PlaceDto>
}
