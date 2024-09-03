package com.eoyeongbooyeong.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _sideEffects = MutableSharedFlow<MyPageSideEffect>()
    val sideEffects: SharedFlow<MyPageSideEffect>
        get() = _sideEffects.asSharedFlow()

    fun navigateToWebView(url: String) {
        viewModelScope.launch {
            _sideEffects.emit(MyPageSideEffect.NavigateToWebView(url))
        }
    }

    fun cancelAuth() {
        viewModelScope.launch {
            authRepository.withDraw(
                authRepository.getAccessToken()
            ).onSuccess {
                authRepository.clearInfo()
                _sideEffects.emit(MyPageSideEffect.RestartApp)
            }.onFailure(Timber::e)
        }
    }
}
