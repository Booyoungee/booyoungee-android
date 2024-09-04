package com.eoyeongbooyeong.mypage.edit_nickname

data class EditNicknameState(
    val nickname: String = "",
    val isAvailable: Boolean = false,
    val isError: Boolean = false,
)
