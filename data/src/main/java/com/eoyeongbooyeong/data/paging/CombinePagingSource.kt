package com.eoyeongbooyeong.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eoyeongbooyeong.data.datasource.MovieDataSource
import com.eoyeongbooyeong.data.datasource.TourInfoOpenApiDataSource
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import javax.inject.Inject

class CombinePagingSource @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val tourInfoOpenApiDataSource: TourInfoOpenApiDataSource,
    private val keyword: String,
    private val updateTotalCount: (Int) -> Unit,
    private val updateIsLoading: (Boolean) -> Unit,
) : PagingSource<Int, PlaceDetailsEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlaceDetailsEntity> {
        val page = params.key ?: 1
        updateIsLoading(true)

        try {
            val movieResult = movieDataSource.searchOnKeyword(
                numOfRows = params.loadSize,
                pageNo = page,
                keyword = keyword
            )
            val tourResult = tourInfoOpenApiDataSource.searchOnKeyword(
                numOfRows = params.loadSize,
                pageNo = page,
                keyword = keyword
            )

            val movieResponse = movieResult.contents.map { it.toDomain().toPlaceDetailsEntity() }
            val tourResponse = tourResult.contents.map { it.toDomain().toPlaceDetailsEntity() }

            val totalCount = movieResult.pageable.totalElements + tourResult.pageable.totalElements
            updateTotalCount(totalCount)

            if (
                movieResult.contents.isEmpty() && tourResult.contents.isEmpty()
            ) {
                updateIsLoading(false)
            }

            return LoadResult.Page(
                data = movieResponse + tourResponse,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movieResponse.isEmpty() && tourResponse.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            updateIsLoading(false)

            return LoadResult.Page(
                data = emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = null
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlaceDetailsEntity>): Int? {
        return state.anchorPosition
    }
}