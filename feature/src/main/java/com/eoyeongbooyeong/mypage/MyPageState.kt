package com.eoyeongbooyeong.mypage

data class MyPageState(
    val nickname: String = "",
    val isLogoutDialogVisible: Boolean = false,
    val isWithdrawDialogVisible: Boolean = false,
)
