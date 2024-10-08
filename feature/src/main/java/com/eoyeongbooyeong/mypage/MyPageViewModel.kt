package com.eoyeongbooyeong.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.AuthRepository
import com.eoyeongbooyeong.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(MyPageState())
    val state: StateFlow<MyPageState>
        get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<MyPageSideEffect>()
    val sideEffects: SharedFlow<MyPageSideEffect>
        get() = _sideEffects.asSharedFlow()

    init {
        getUserNickname()
    }

    fun navigateToWebView(url: String) {
        viewModelScope.launch {
            _sideEffects.emit(MyPageSideEffect.NavigateToWebView(url))
        }
    }

    fun navigateToEditNickname() {
        viewModelScope.launch {
            _sideEffects.emit(MyPageSideEffect.NavigateToEditNickname)
        }
    }

    fun navigateToMyReview() {
        viewModelScope.launch {
            _sideEffects.emit(MyPageSideEffect.NavigateToMyReview)
        }
    }

    fun navigateToBookmark() {
        viewModelScope.launch {
            _sideEffects.emit(MyPageSideEffect.NavigateToBookmark)
        }
    }

    fun controlLogoutDialog() {
        _state.value =
            _state.value.copy(isLogoutDialogVisible = !_state.value.isLogoutDialogVisible)
    }

    fun controlWithDrawDialog() {
        _state.value =
            _state.value.copy(isWithdrawDialogVisible = !_state.value.isWithdrawDialogVisible)
    }

    fun withDraw() {
        viewModelScope.launch {
            authRepository.withDraw(
                authRepository.getAccessToken()
            ).onSuccess {
                authRepository.clearInfo()
                _sideEffects.emit(MyPageSideEffect.RestartApp)
            }.onFailure(Timber::e)
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout(
                authRepository.getAccessToken()
            ).onSuccess {
                authRepository.clearInfo()
                _sideEffects.emit(MyPageSideEffect.RestartApp)
            }.onFailure(Timber::e)
        }
    }

    fun getUserNickname() {
        viewModelScope.launch {
            userRepository.getUserNickname()
                .onSuccess {
                    _state.value = _state.value.copy(nickname = it)
                }.onFailure(Timber::e)
        }
    }
}
