package com.eoyeongbooyeong.auth.login

sealed class LoginSideEffect {
    data object NavigateToSignUp : LoginSideEffect()
    data object NavigateToHome : LoginSideEffect()
}
