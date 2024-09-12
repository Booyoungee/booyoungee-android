package com.eoyeongbooyeong.places.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.PlaceRepository
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

        suspend fun getPlaceDetailsInfo(
            placeId: Int,
            placeType: String,
        ) {
            viewModelScope.launch {
                placeRepository
                    .getPlaceDetails(placeId, placeType)
                    .onSuccess {
                        _state.value =
                            _state.value.copy(
                                placeDetailsInfo = it,
                            )
                        Timber.tag("PlaceDetailsViewModel").d(it.toString())
                    }.onFailure {
                        _sideEffects.emit(KakaoMapSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }
    }
