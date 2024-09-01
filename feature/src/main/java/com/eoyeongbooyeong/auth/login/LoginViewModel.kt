package com.eoyeongbooyeong.auth.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.AuthRepository
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _sideEffects = MutableSharedFlow<LoginSideEffect>()
    val sideEffects: SharedFlow<LoginSideEffect> get() = _sideEffects.asSharedFlow()

    fun startKakaoLogin(isKakaoTalkAvailable: Boolean) {
        viewModelScope.launch {
            if (isKakaoTalkAvailable) {
                _sideEffects.emit(LoginSideEffect.StartKakaoTalkLogin)
            } else {
                _sideEffects.emit(LoginSideEffect.StartKakaoWebLogin)
            }
        }
    }

    fun handleLoginResult(token: OAuthToken?, error: Throwable?) {
        viewModelScope.launch {
            if (error != null) {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    handleLoginError(error.message ?: "Login Cancelled")
                } else {
                    _sideEffects.emit(LoginSideEffect.StartKakaoWebLogin)
                }
            } else if (token != null) {
                sendTokenToServer(token.accessToken)
                Timber.tag("KAKAO LOGIN").d("로그인 성공 ${token.accessToken}")
            } else {
                handleLoginError("Unknown error")
            }
        }
    }

    private fun sendTokenToServer(
        accessToken: String
    ) {
//        viewModelScope.launch {
//            authRepository.postLogin(accessToken)
//                .onSuccess { response ->
//                    authRepository.setTokens(response.accessToken, response.refreshToken)
//                    _loginSideEffects.emit(
//                        LoginSideEffect.NavigateToHome
//                    )
//                }.onFailure { throwable ->
//                    에러 코드 분석해서 오류 메시지 or 로그인 이동
//                    val errorMessage = throwable.localizedMessage ?: "Unknown error"
//                    handleLoginError(errorMessage)
//                }
//        }
    }


    private fun handleLoginError(errorMessage: String) {
        viewModelScope.launch {
            _sideEffects.emit(LoginSideEffect.LoginError(errorMessage))
        }
    }

    fun navigateToSignUp() {
        viewModelScope.launch {
            _sideEffects.emit(LoginSideEffect.NavigateToSignUp)
        }
    }

    fun navigateToHome() {
        viewModelScope.launch {
            _sideEffects.emit(LoginSideEffect.NavigateToHome)
        }
    }
}
