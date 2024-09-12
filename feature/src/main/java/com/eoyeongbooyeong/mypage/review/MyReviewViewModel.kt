package com.eoyeongbooyeong.mypage.review

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(MyReviewState())
    val state: StateFlow<MyReviewState>
        get() = _state.asStateFlow()

    init {
        viewModelScope.launch { // 정상 작동 확인
            reviewRepository.getMyReviews().onSuccess {
                Log.e("TAG", it.toString())
            }.onFailure {
                Log.e("TAG", it.message.toString())
            }
        }
    }
}
