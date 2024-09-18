package com.eoyeongbooyeong.stamp

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class StampState(
    val userName: String = "부영이",
    val stampList: ImmutableList<String> = persistentListOf(),
    val collectedStampList: ImmutableList<String> = persistentListOf(),
)
