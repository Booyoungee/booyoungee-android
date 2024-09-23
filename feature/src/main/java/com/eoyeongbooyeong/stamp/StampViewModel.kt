package com.eoyeongbooyeong.stamp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.StampRepository
import com.eoyeongbooyeong.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StampViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val stampRepository: StampRepository
) : ViewModel() {
    private val _state = MutableStateFlow(StampState())
    val state: StateFlow<StampState>
        get() = _state.asStateFlow()

    init {
        getUserNickname()
        getMyStampList()
        getNearbyStampList()
    }

    private fun getUserNickname() {
        viewModelScope.launch {
            userRepository.getUserNickname().onSuccess { nickname ->
                _state.value = _state.value.copy(userName = nickname)
            }.onFailure(Timber::e)
        }
    }

    private fun getMyStampList() {
        viewModelScope.launch {
            stampRepository.getMyStampList().onSuccess { stampList ->
                _state.value = _state.value.copy(myStampList = stampList.toPersistentList())
            }.onFailure(Timber::e)
        }
    }

    private fun getNearbyStampList() {
        viewModelScope.launch {
            stampRepository.getNearbyStampList(
                userX = 129.1709112.toString(),
                userY = 35.1593662.toString(),
                radius = 1000
            ).onSuccess { stampList ->
                _state.value = _state.value.copy(nearbyStampList = stampList.toPersistentList())
            }.onFailure(Timber::e)
        }
    }
}
