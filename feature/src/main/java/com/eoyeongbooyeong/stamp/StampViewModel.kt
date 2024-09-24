package com.eoyeongbooyeong.stamp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.StampRepository
import com.eoyeongbooyeong.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StampViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val stampRepository: StampRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(StampState())
    val state: StateFlow<StampState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<StampSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        getUserNickname()
    }

    private fun getUserNickname() {
        viewModelScope.launch {
            userRepository.getUserNickname().onSuccess { nickname ->
                _state.value = _state.value.copy(userName = nickname)
            }.onFailure(Timber::e)
        }
    }

    fun getMyStampList() {
        viewModelScope.launch {
            stampRepository.getMyStampList().onSuccess { stampList ->
                _state.value = _state.value.copy(myStampList = stampList.toPersistentList())
            }.onFailure(Timber::e)
        }
    }

    fun setMyLocation(userX: String, userY: String) {
        _state.value = _state.value.copy(myX = userX, myY = userY)
    }

    fun getNearbyStampList(
        userX: String,
        userY: String,
    ) {
        viewModelScope.launch {
            stampRepository.getNearbyStampList(
                userX = userX,
                userY = userY,
                radius = RADIUS
            ).onSuccess { stampList ->
                _state.value = _state.value.copy(nearbyStampList = stampList.toPersistentList())
            }.onFailure(Timber::e)
        }
    }

    fun stampPlace(
        placeName: String,
        placeId: Int,
        type: String,
        mapX: String,
        mapY: String,
    ) {
        viewModelScope.launch {
            stampRepository.stampPlace(
                placeId = placeId,
                type = type,
                userX = state.value.myX,
                userY = state.value.myY,
                x = mapX,
                y = mapY
            ).onSuccess {
                _sideEffect.emit(StampSideEffect.ShowToast(placeName))
                getMyStampList()
                _state.value = _state.value.copy(
                    nearbyStampList = state.value.nearbyStampList.filter { it.placeId != placeId }
                        .toPersistentList()
                )
            }.onFailure(Timber::e)
        }
    }

    fun setMyLocationError(isLocationError: Boolean = true) {
        _state.value = _state.value.copy(isLocationError = isLocationError)
    }

    companion object {
        private const val RADIUS = 100
    }
}
