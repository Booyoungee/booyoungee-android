package com.eoyeongbooyeong.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _sideEffects = MutableSharedFlow<LoginSideEffect>()
    val sideEffects: SharedFlow<LoginSideEffect> get() = _sideEffects.asSharedFlow()

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
