package com.eoyeongbooyeong.data.datasourceImpl

import com.eoyeongbooyeong.data.datasource.StampDataSource
import com.eoyeongbooyeong.data.service.StampService
import javax.inject.Inject

class StampDataSourceImpl @Inject constructor(
    private val stampService: StampService,
) : StampDataSource {
    override suspend fun getMyStampList() = stampService.getMyStampList().data.contents

    override suspend fun getNearbyStampList(
        userX: String,
        userY: String,
        radius: Int,
    ) = stampService.getNearbyStampList(
        userX, userY, radius
    ).data.contents
}
