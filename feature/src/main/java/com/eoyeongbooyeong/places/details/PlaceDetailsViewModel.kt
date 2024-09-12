package com.eoyeongbooyeong.places.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import com.eoyeongbooyeong.domain.repository.ReviewRepository
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
class PlaceDetailsViewModel
    @Inject
    constructor(
        private val placeRepository: PlaceRepository,
        private val reviewRepository: ReviewRepository
    ) : ViewModel() {
    private val _state = MutableStateFlow(PlaceDetailsState())
    val state: StateFlow<PlaceDetailsState>
        get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<PlaceDetailsSideEffect>()
    val sideEffects: SharedFlow<PlaceDetailsSideEffect>
        get() = _sideEffects.asSharedFlow()

    fun updateState(newState: PlaceDetailsState) {
        _state.value = newState.copy()
    }

    fun sendSideEffect(sideEffect: PlaceDetailsSideEffect) {
        viewModelScope.launch {
            _sideEffects.emit(sideEffect)
        }
    }

    // 장소 상세 정보를 가져오는 함수
    fun getPlaceDetailsInfo(
        placeId: Int,
        placeType: String,
    ) {
        viewModelScope.launch {
            placeRepository
                .getPlaceDetails(placeId, placeType)
                .onSuccess {
                    _state.value =
                        _state.value.copy(
                            placeInfoEntity = it,
                            isLiked = it.me.hasLike,
                            isBookmarked = it.me.hasBookmark,
                            likeCount = it.likeCount,
                        )
                    Timber.tag("PlaceDetailsViewModel").d(it.toString())
                }.onFailure {
                    _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
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
                            bookMarkId = bookmarkResponse.bookmarkId,
                        )
                    // 북마크 추가 성공 시 상태 업데이트
                    Timber.tag("PlaceDetailsViewModel").d(bookmarkResponse.toString())
                }.onFailure {
                    _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                }
        }
    }

    // 북마크 삭제 요청
    fun deleteBookMark(bookMarkId: Int) {
        viewModelScope.launch {
            placeRepository
                .deleteBookMark(bookMarkId)
                .onSuccess { bookmarkResponse ->
                    _state.value =
                        _state.value.copy(
                            isBookmarked = false,
                            bookMarkId = bookmarkResponse.bookmarkId,
                        )
                    // 북마크 삭제 성공 시 상태 업데이트
                    Timber.tag("PlaceDetailsViewModel").d(bookmarkResponse.toString())
                }.onFailure {
                    _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                }
        }
    }

    // 좋아요 추가 요청
    fun postLike(placeId: Int) {
        viewModelScope.launch {
            placeRepository
                .postLike(placeId)
                .onSuccess { likeResponse ->
                    _state.value =
                        _state.value.copy(
                            isLiked = true,
                            likeId = likeResponse.likeId,
                        )
                    state.value.likeCount++
                    // 좋아요 추가 성공 시 상태 업데이트
                    Timber.tag("PlaceDetailsViewModel").d(likeResponse.toString())
                }.onFailure {
                    _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                }
        }
    }

    // 좋아요 삭제 요청
    fun deleteLike(likeId: Int) {
        viewModelScope.launch {
            placeRepository
                .deleteLike(likeId)
                .onSuccess { likeResponse ->
                    _state.value =
                        _state.value.copy(
                            isLiked = false,
                            likeId = likeResponse.likeId,
                        )
                    state.value.likeCount--
                    // 좋아요 삭제 성공 시 상태 업데이트
                    Timber.tag("PlaceDetailsViewModel").d(likeResponse.toString())
                }.onFailure {
                    _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                }
        }
    }

    fun getReviews(placeId: Int) {
        viewModelScope.launch {
            reviewRepository.getReviews(placeId)
                .onSuccess {
                    _state.value = _state.value.copy(reviewList = it)
                }.onFailure {
                    _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                }
        }
    }
}
