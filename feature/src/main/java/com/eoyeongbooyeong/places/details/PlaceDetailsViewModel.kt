package com.eoyeongbooyeong.places.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import com.eoyeongbooyeong.domain.repository.ReviewRepository
import com.eoyeongbooyeong.domain.repository.UserRepository
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
        private val userRepository: UserRepository,
        private val placeRepository: PlaceRepository,
        private val reviewRepository: ReviewRepository,
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
                            )
                        state.value.bookMarkCount++
                        // 북마크 추가 성공 시 상태 업데이트
                        Timber.tag("PlaceDetailsViewModel").d(bookmarkResponse.toString())
                    }.onFailure {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
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
                        state.value.bookMarkCount--
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
        fun deleteLike(placeId: Int) {
            viewModelScope.launch {
                placeRepository
                    .deleteLike(placeId)
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
                reviewRepository
                    .getReviews(placeId)
                    .onSuccess {
                        _state.value = _state.value.copy(reviewList = it)
                    }.onFailure {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        // 유저 차단
        fun postBlockUser(
            blockUserId: Int,
            placeId: Int,
        ) {
            viewModelScope.launch {
                userRepository
                    .blockUser(blockUserId)
                    .onSuccess {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast("해당 사용자를 차단했습니다."))
                        getReviews(placeId)
                    }.onFailure {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }

        // 리뷰 신고
        fun postAccuseReview(commentId: Int) {
            viewModelScope.launch {
                reviewRepository
                    .accuseReview(commentId)
                    .onSuccess {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast("해당 리뷰를 신고했습니다."))
                    }.onFailure {
                        _sideEffects.emit(PlaceDetailsSideEffect.ShowToast(it.message.toString()))
                    }
            }
        }
    }
