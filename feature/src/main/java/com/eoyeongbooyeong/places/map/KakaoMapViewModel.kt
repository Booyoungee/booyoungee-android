package com.eoyeongbooyeong.places.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import com.eoyeongbooyeong.domain.repository.PlaceRepository
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
class KakaoMapViewModel
    @Inject
    constructor(
        private val placeRepository: PlaceRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(KakaoMapState())
        val state: StateFlow<KakaoMapState>
            get() = _state.asStateFlow()

        private val _sideEffects = MutableSharedFlow<KakaoMapSideEffect>()
        val sideEffects: SharedFlow<KakaoMapSideEffect>
            get() = _sideEffects.asSharedFlow()

        fun sendSideEffect(sideEffect: KakaoMapSideEffect) {
            viewModelScope.launch {
                _sideEffects.emit(sideEffect)
            }
        }

        fun updateState(newState: KakaoMapState) {
            _state.value = newState.copy()
        }

        fun onMarkerClicked(place: PlaceInfoEntity) {
            updateState(
                state.value.copy(
                    showDetailBox = true,
                    selectedPlace = place,
                )
            )
        }

        fun resetClickedPlace() {
            updateState(
                state.value.copy(
                    showDetailBox = false,
                    selectedPlace = null,
                )
            )
        }

        fun getMoviePlaceListWitFilter(filter: String = "star") {
            _state.value = _state.value.copy(isLoading = true)
            viewModelScope.launch {
                placeRepository
                    .getMoviePlacesWithCategory(filter)
                    .onSuccess {
                        _state.value =
                            state.value.copy(
                                placeList = it,
                                isLoading = false,
                            )
                    }.onFailure {
                        _sideEffects.emit(KakaoMapSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        fun getLocalStorePlaceListWitFilter(filter: String = "star") {
            _state.value = _state.value.copy(isLoading = true)

            viewModelScope.launch {
                placeRepository
                    .getLocalStorePlacesWithCategory(filter)
                    .onSuccess {
                        _state.value =
                            state.value.copy(
                                placeList = it,
                                isLoading = false,
                            )
                    }.onFailure {
                        _sideEffects.emit(KakaoMapSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        fun getTourPlaceListWitFilter(filter: String = "star") {
            _state.value = _state.value.copy(isLoading = true)

            viewModelScope.launch {
                placeRepository
                    .getTourPlacesWithCategory(filter)
                    .onSuccess {
                        _state.value =
                            state.value.copy(
                                placeList = it,
                                isLoading = false,
                            )
                    }.onFailure {
                        _sideEffects.emit(KakaoMapSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }
    }
