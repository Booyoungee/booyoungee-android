package com.eoyeongbooyeong.data.repositoryImpl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eoyeongbooyeong.data.datasource.MovieDataSource
import com.eoyeongbooyeong.data.datasource.TourInfoOpenApiDataSource
import com.eoyeongbooyeong.data.paging.CombinePagingSource
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import com.eoyeongbooyeong.domain.repository.TourInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class TourInfoOpenApiRepositoryImpl @Inject constructor(
    private val tourInfoOpenApiDataSource: TourInfoOpenApiDataSource,
    private val movieDataSource: MovieDataSource,
) : TourInfoRepository {
    private val _totalCount = MutableStateFlow(0)
    private val _isLoading = MutableStateFlow(false)

    override fun searchOnKeyword(
        numOfRows: Int,
        pageNo: Int,
        keyword: String,
    ): Flow<PagingData<PlaceDetailsEntity>> =
        Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = {
                CombinePagingSource(
                    movieDataSource,
                    tourInfoOpenApiDataSource,
                    keyword,
                    ::updateTotalCount,
                    ::updateIsLoading
                )
            }
        ).flow

    override fun getTotalCount(): Flow<Int> = _totalCount

    override fun getIsLoading(): Flow<Boolean> = _isLoading

    private fun updateTotalCount(count: Int) {
        _totalCount.value = count
    }

    private fun updateIsLoading(loading: Boolean) {
        _isLoading.value = loading
    }
}