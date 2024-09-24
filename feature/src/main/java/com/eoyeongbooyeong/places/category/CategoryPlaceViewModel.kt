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
import timber.log.Timber
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

        fun sendSideEffect(sideEffect: CategorySideEffect) {
            viewModelScope.launch {
                _sideEffects.emit(sideEffect)
            }
        }

        fun updateState(newState: CategoryState) {
            _state.value = newState.copy()
        }

    fun updatePlaceType(placeType: String) {
        updateState(
            state.value.copy(
                placeType = placeType,
            )
        )
    }

        fun getMoviePlaceListWitFilter(filter: String) {
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
                        // _sideEffects.emit(CategorySideEffect.ShowToast(it.message.toString()))
                        _sideEffects.emit(CategorySideEffect.ShowToast("OPEN API 내 문제가 발생했습니다."))
                    }
            }
        }

        fun getLocalStorePlaceListWitFilter(filter: String) {
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
                        _sideEffects.emit(CategorySideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        fun getTourPlaceListWitFilter(filter: String) {
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
                        _sideEffects.emit(CategorySideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        // 북마크 추가 요청
        fun postBookMark(
            placeId: Int,
            placeType: String,
        ) {
            viewModelScope.launch {
                placeRepository
                    .postBookMark(placeId, placeType)
                    .onSuccess { bookmarkResponse ->
                        _state.value =
                            _state.value.copy(
                                isBookmarked = true,
                            )
                        // 북마크 추가 성공 시 상태 업데이트
                        Timber.tag("PlaceDetailsViewModel").d(bookmarkResponse.toString())
                    }.onFailure {
                        _sideEffects.emit(CategorySideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        // 북마크 삭제 요청
        fun deleteBookMark(placeId: Int) {
            viewModelScope.launch {
                placeRepository
                    .deleteBookMark(placeId)
                    .onSuccess { bookmarkResponse ->
                        _state.value =
                            _state.value.copy(
                                isBookmarked = false,
                            )
                        // 북마크 삭제 성공 시 상태 업데이트
                        Timber.tag("PlaceDetailsViewModel").d(bookmarkResponse.toString())
                    }.onFailure {
                        _sideEffects.emit(CategorySideEffect.ShowToast(it.message.toString()))
                    }
            }
        }
    }
