package com.eoyeongbooyeong.domain.repository

import androidx.paging.PagingData
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import kotlinx.coroutines.flow.Flow

interface TourInfoRepository {
    fun searchOnKeyword(
        numOfRows: Int,
        pageNo: Int,
        keyword: String,
    ): Flow<PagingData<PlaceDetailsEntity>>

    fun getTotalCount(): Flow<Int>

    fun getIsLoading(): Flow<Boolean>
}
