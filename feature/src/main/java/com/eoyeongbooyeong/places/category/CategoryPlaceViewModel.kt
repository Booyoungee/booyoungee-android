package com.eoyeongbooyeong.places.category

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
import javax.inject.Inject

@HiltViewModel
class CategoryPlaceViewModel
    @Inject
    constructor(
        private val placeRepository: PlaceRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(CategoryState())
        val state: StateFlow<CategoryState>
            get() = _state.asStateFlow()

        private val _sideEffects = MutableSharedFlow<CategorySideEffect>()
        val sideEffects: SharedFlow<CategorySideEffect>
            get() = _sideEffects.asSharedFlow()

        init {
            getMoviePlaceListWitFilter("star")
            getLocalStorePlaceListWitFilter("star")
            getTourPlaceListWitFilter("star")
        }

        fun getMoviePlaceListWitFilter(filter: String) {
            viewModelScope.launch {
                placeRepository
                    .getMoviePlacesWithCategory(filter)
                    .onSuccess {
                        _state.value =
                            state.value.copy(
                                placeList = it,
                            )
                    }.onFailure {
                        _sideEffects.emit(CategorySideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        fun getLocalStorePlaceListWitFilter(filter: String) {
            viewModelScope.launch {
                placeRepository
                    .getLocalStorePlacesWithCategory(filter)
                    .onSuccess {
                        _state.value =
                            state.value.copy(
                                placeList = it,
                            )
                    }.onFailure {
                        _sideEffects.emit(CategorySideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        fun getTourPlaceListWitFilter(filter: String) {
            viewModelScope.launch {
                placeRepository
                    .getTourPlacesWithCategory(filter)
                    .onSuccess {
                        _state.value =
                            state.value.copy(
                                placeList = it,
                            )
                    }.onFailure {
                        _sideEffects.emit(CategorySideEffect.ShowToast(it.message.toString()))
                    }
            }
        }
    }
