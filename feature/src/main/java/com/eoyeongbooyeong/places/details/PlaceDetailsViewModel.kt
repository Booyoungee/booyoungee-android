package com.eoyeongbooyeong.places.details

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

        fun postBookMark(
            placeId: Int,
            placeType: String,
        ) {
            viewModelScope.launch {
                placeRepository
                    .postBookMark(placeId, placeType)
                    .onSuccess {
                        _state.value =
                            _state.value.copy(isBookmarked = it.isBookMarked, bookMarkId = it.bookmarkId)
                    }.onFailure {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        fun deleteBookMark(bookMarkId: Int) {
            viewModelScope.launch {
                placeRepository
                    .deleteBookMark(bookMarkId)
                    .onSuccess {
                        _state.value =
                            _state.value.copy(isBookmarked = it.isBookMarked, bookMarkId = it.bookmarkId)
                    }.onFailure {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        fun postLike(placeId: Int) {
            viewModelScope.launch {
                placeRepository
                    .postLike(placeId)
                    .onSuccess {
                        _state.value = _state.value.copy(isLiked = it.isLiked, likeId = it.likeId)
                    }.onFailure {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        fun deleteLike(likeId: Int) {
            viewModelScope.launch {
                placeRepository
                    .deleteLike(likeId)
                    .onSuccess {
                        _state.value = _state.value.copy(isLiked = it.isLiked, likeId = it.likeId)
                    }.onFailure {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }
    }