package com.eoyeongbooyeong.data.service

import com.eoyeongbooyeong.data.dto.response.BaseContents
import com.eoyeongbooyeong.data.dto.response.BaseResponse
import com.eoyeongbooyeong.data.dto.response.PlaceDto
import retrofit2.http.GET

interface BookmarkService {
    @GET("api/v1/bookmark/me")
    suspend fun getBookmarkList(): BaseResponse<BaseContents<PlaceDto>>
}
