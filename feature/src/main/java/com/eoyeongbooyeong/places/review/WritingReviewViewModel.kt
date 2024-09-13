package com.eoyeongbooyeong.places.review

import androidx.lifecycle.ViewModel
import com.eoyeongbooyeong.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.viewModelScope


@HiltViewModel
class WritingReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
): ViewModel() {
        private val _state = MutableStateFlow(WritingReviewState())
        val state: StateFlow<WritingReviewState>
            get() = _state.asStateFlow()

        private val _sideEffects = MutableSharedFlow<WritingReviewSideEffect>()
        val sideEffects: SharedFlow<WritingReviewSideEffect>
            get() = _sideEffects.asSharedFlow()

        fun updateState(newState: WritingReviewState) {
            _state.value = newState.copy()
        }

        fun postReview(placeId: Int, content: String, stars: Int) {
            viewModelScope.launch {
                reviewRepository.writeReview(placeId, content, stars)
                    .onSuccess {
                        _sideEffects.emit(WritingReviewSideEffect.ShowToast("리뷰가 작성되었습니다."))
                        _sideEffects.emit(WritingReviewSideEffect.NavigateReviewFinish)
                    }.onFailure {
                        _sideEffects.emit(WritingReviewSideEffect.ShowToast(it.message.toString()))
                    }
            }

        }
    }
