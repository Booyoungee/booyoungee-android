package com.eoyeongbooyeong.places.details

import androidx.lifecycle.ViewModel
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PlaceDetailsViewModel
    @Inject
    constructor(
        private val placeRepository: PlaceRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(PlaceDetailsState())
        val state: StateFlow<PlaceDetailsState>
            get() = _state.asStateFlow()

        private val _sideEffects = MutableSharedFlow<PlaceDetailsSideEffect>()
        val sideEffects: SharedFlow<PlaceDetailsSideEffect>
            get() = _sideEffects.asSharedFlow()
    }
