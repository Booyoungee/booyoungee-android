package com.eoyeongbooyeong.mypage.review

import com.eoyeongbooyeong.domain.entity.MyReviewEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MyReviewState(
    val myReviewList: ImmutableList<MyReviewEntity> = persistentListOf(),
)
