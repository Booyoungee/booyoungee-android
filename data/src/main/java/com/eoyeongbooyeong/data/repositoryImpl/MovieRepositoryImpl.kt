package com.eoyeongbooyeong.data.repositoryImpl

import com.eoyeongbooyeong.data.datasource.MovieDataSource
import com.eoyeongbooyeong.domain.entity.MoviePlaceEntity
import com.eoyeongbooyeong.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource,
) : MovieRepository {
    override suspend fun searchOnKeyword(
        numOfRows: Int,
        pageNo: Int,
        keyword: String,
    ): Result<List<MoviePlaceEntity>> = runCatching {
        movieDataSource.searchOnKeyword(numOfRows, pageNo, keyword).contents.map { it.toDomain() }
    }
}