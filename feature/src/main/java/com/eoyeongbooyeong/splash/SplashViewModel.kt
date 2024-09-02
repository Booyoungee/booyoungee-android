package com.eoyeongbooyeong.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _sideEffects = MutableSharedFlow<SplashSideEffect>()
    val sideEffects: SharedFlow<SplashSideEffect> get() = _sideEffects.asSharedFlow()

    fun showSplash() {
        viewModelScope.launch {
            authRepository.clearInfo()
            delay(SPLASH_DURATION)

            if (authRepository.isAlreadyLogin()) {
                _sideEffects.emit(SplashSideEffect.NavigateToHome)
            } else {
                _sideEffects.emit(SplashSideEffect.NavigateToLogin)
            }
        }
    }

    companion object {
        const val SPLASH_DURATION = 1200L
    }
}