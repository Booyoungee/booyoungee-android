package com.eoyeongbooyeong.auth.signup

data class SignUpState(
    val name: String = "",
    val isAvailable: Boolean = false,
    val isError: Boolean = false,
)
