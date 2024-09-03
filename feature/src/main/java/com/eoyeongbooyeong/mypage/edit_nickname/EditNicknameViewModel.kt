package com.eoyeongbooyeong.mypage.edit_nickname

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class EditNicknameViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(EditNicknameState())
    val state: StateFlow<EditNicknameState>
        get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<EditNicknameSideEffect>()
    val sideEffects: SharedFlow<EditNicknameSideEffect>
        get() = _sideEffects.asSharedFlow()

    fun updateName(name: String) {
        _state.value = _state.value.copy(
            nickname = name,
            isAvailable = false
        )
    }

    fun isAvailableNickname() {
        viewModelScope.launch {
            userRepository.isAvailableNickname(state.value.nickname)
                .onSuccess {
                    _state.value = _state.value.copy(
                        isAvailable = true,
                        isError = false
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

    fun setNewNickname(nickname: String) {
        viewModelScope.launch {
            userRepository.setUserNickname(nickname)
                .onSuccess {
                    _sideEffects.emit(EditNicknameSideEffect.NavigateUp)
                }.onFailure(Timber::e)
        }
    }
}
