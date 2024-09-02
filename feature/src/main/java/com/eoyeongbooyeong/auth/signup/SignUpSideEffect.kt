package com.eoyeongbooyeong.auth.signup

sealed class SignUpSideEffect {
    data class SignUpError(val errorMessage: String) : SignUpSideEffect()
    data object NavigateToHome : SignUpSideEffect()
}