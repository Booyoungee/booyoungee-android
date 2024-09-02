package com.eoyeongbooyeong.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.AuthRepository
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
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
                    handleLoginError(error.message ?: LOGIN_CANCELLED)
                } else {
                    _sideEffects.emit(LoginSideEffect.StartKakaoWebLogin)
                }
            } else if (token != null) {
                sendTokenToServer(token.accessToken, token.refreshToken)
                Timber.tag("KAKAO LOGIN").d("kakao access token : ${token.accessToken}")
                Timber.tag("KAKAO LOGIN").d("kakao refresh token : ${token.refreshToken}")
            } else {
                handleLoginError(UNKNOWN_ERROR)
            }
        }
    }

    private fun sendTokenToServer(
        accessToken: String,
        refreshToken: String,
    ) {
        viewModelScope.launch {
            authRepository.login(
                accessToken = accessToken,
                refreshToken = refreshToken
            ).onSuccess { response ->
                authRepository.setTokens(response.accessToken, response.refreshToken)
                navigateToHome()
            }.onFailure { throwable ->
                if (throwable is HttpException && throwable.code() == NOT_FOUND_USER) {
                    navigateToSignUp()
                } else {
                    handleLoginError(throwable.localizedMessage ?: UNKNOWN_ERROR)
                }
            }
        }
    }

    private suspend fun handleLoginError(errorMessage: String) =
        _sideEffects.emit(LoginSideEffect.LoginError(errorMessage))

    private suspend fun navigateToSignUp() = _sideEffects.emit(LoginSideEffect.NavigateToSignUp)

    private suspend fun navigateToHome() = _sideEffects.emit(LoginSideEffect.NavigateToHome)

    companion object {
        private const val NOT_FOUND_USER = 404
        private const val LOGIN_CANCELLED = "Login Cancelled"
        private const val UNKNOWN_ERROR = "Unknown error"
    }
}
