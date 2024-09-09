package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.BookmarkDataSource
import com.eoyeongbooyeong.data.dto.response.PlaceDto
import com.eoyeongbooyeong.data.service.BookmarkService
import javax.inject.Inject

class BookmarkDataSourceImpl @Inject constructor(
    private val bookmarkService: BookmarkService,
) : BookmarkDataSource {
    override suspend fun getBookmarkList(): List<PlaceDto> {
        return bookmarkService.getBookmarkList().data
    }
}
