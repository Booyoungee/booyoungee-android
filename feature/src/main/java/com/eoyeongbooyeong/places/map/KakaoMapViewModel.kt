package com.eoyeongbooyeong.places.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import com.eoyeongbooyeong.places.category.CategorySideEffect
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

    fun getMoviePlaceListWitFilter(filter: String = "star") { //TODO
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

    fun getLocalStorePlaceListWitFilter(filter: String = "default") {
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

    fun getTourPlaceListWitFilter(filter: String = "default") {
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
