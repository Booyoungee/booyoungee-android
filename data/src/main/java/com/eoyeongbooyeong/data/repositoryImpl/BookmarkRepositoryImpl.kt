package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.BookmarkDataSource
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDataSource: BookmarkDataSource,
) : BookmarkRepository {
    override suspend fun getBookmarkList(): Result<List<PlaceInfoEntity>> = runCatching {
        bookmarkDataSource.getBookmarkList().map { it.toDomain() }
    }
}
