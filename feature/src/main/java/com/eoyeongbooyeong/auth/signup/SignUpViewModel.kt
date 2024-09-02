package com.eoyeongbooyeong.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.auth.login.LoginSideEffect
import com.eoyeongbooyeong.domain.repository.AuthRepository
import com.eoyeongbooyeong.domain.repository.UserRepository
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.TokenManagerProvider
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState>
        get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<SignUpSideEffect>()
    val sideEffects: SharedFlow<SignUpSideEffect>
        get() = _sideEffects.asSharedFlow()

    fun updateName(name: String) {
        _state.value = _state.value.copy(
            name = name,
            isAvailable = false
        )
    }

    fun isAvailableNickname() {
        viewModelScope.launch {
            userRepository.isAvailableNickname(state.value.name)
                .onSuccess {
                    _state.value = _state.value.copy(
                        isAvailable = true,
                    )
                }
                .onFailure {
                    _state.value = _state.value.copy(
                        isAvailable = false,
                        isError = true
                    )
                }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            authRepository.signup(
                TokenManagerProvider.instance.manager.getToken()?.accessToken.toString(),
                TokenManagerProvider.instance.manager.getToken()?.refreshToken.toString(),
                state.value.name
            ).onSuccess {
                authRepository.setTokens(it.accessToken, it.refreshToken)

                _sideEffects.emit(SignUpSideEffect.NavigateToHome)
            }.onFailure {
                _sideEffects.emit(SignUpSideEffect.SignUpError(it.message ?: "회원가입에 실패했습니다"))
            }
        }
    }
}
