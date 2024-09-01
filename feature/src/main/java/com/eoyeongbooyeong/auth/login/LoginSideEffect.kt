package com.eoyeongbooyeong.auth.login

sealed class LoginSideEffect {
    data class LoginError(val errorMessage: String) : LoginSideEffect()
    data object StartKakaoTalkLogin : LoginSideEffect()
    data object StartKakaoWebLogin : LoginSideEffect()
    data object NavigateToSignUp : LoginSideEffect()
    data object NavigateToHome : LoginSideEffect()
}
