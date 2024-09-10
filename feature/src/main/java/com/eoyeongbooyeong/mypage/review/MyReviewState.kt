package com.eoyeongbooyeong.mypage.review

import com.eoyeongbooyeong.domain.entity.ReviewInfoEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MyReviewState(
    val myReviewList: ImmutableList<ReviewInfoEntity> = persistentListOf(),
)
