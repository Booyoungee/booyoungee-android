package com.eoyeongbooyeong.mypage.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
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
class MyReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(MyReviewState())
    val state: StateFlow<MyReviewState>
        get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<MyReviewSideEffect>()
    val sideEffects: SharedFlow<MyReviewSideEffect>
        get() = _sideEffects.asSharedFlow()

    init {
        getMyReviews()
    }

    private fun getMyReviews() {
        viewModelScope.launch {
            reviewRepository.getMyReviews().onSuccess {
                _state.value = _state.value.copy(myReviewList = it.toImmutableList())
            }.onFailure(Timber::e)
        }
    }

    fun navigateUp() {
        viewModelScope.launch {
            _sideEffects.emit(MyReviewSideEffect.NavigateUp)
        }
    }
}
