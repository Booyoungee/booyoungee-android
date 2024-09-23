package com.eoyeongbooyeong.stamp

import com.eoyeongbooyeong.domain.entity.StampEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class StampState(
    val userName: String = "부영이",
    val myX: String = "129.1709112",
    val myY: String = "35.1593662",
    val nearbyStampList: ImmutableList<StampEntity> = persistentListOf(),
    val myStampList: ImmutableList<StampEntity> = persistentListOf(),
)
