package com.eoyeongbooyeong.mypage.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.BookmarkRepository
import com.eoyeongbooyeong.domain.repository.PlaceRepository
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
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    private val placeRepository: PlaceRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(BookmarkState())
    val state: StateFlow<BookmarkState>
        get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<BookmarkSideEffect>()
    val sideEffects: SharedFlow<BookmarkSideEffect>
        get() = _sideEffects.asSharedFlow()

    fun getBookmarkList() {
        viewModelScope.launch {
            bookmarkRepository.getBookmarkList().onSuccess {
                _state.value = _state.value.copy(myBookmarkList = it.toImmutableList())
            }.onFailure(Timber::e)
        }
    }

    // 북마크 추가 요청
    fun postBookMark(
        placeId: Int,
        placeType: String,
    ) {
        viewModelScope.launch {
            placeRepository.postBookMark(placeId, placeType)
                .onSuccess { bookmarkResponse ->
                    _state.value =
                        _state.value.copy(
                            myBookmarkList = _state.value.myBookmarkList.map { place ->
                                if (place.placeId == placeId.toString()) {
                                    place.copy(me = place.me.copy(hasBookmark = true))
                                } else {
                                    place
                                }
                            }.toImmutableList()
                        )
                }.onFailure(Timber::e)
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
                            myBookmarkList = _state.value.myBookmarkList.map { place ->
                                if (place.placeId == placeId.toString()) {
                                    place.copy(me = place.me.copy(hasBookmark = false))
                                } else {
                                    place
                                }
                            }.toImmutableList()
                        )
                }.onFailure(Timber::e)
        }
    }


    fun navigateUp() {
        viewModelScope.launch {
            _sideEffects.emit(BookmarkSideEffect.NavigateUp)
        }
    }

    fun navigateToPlaceDetail(placeId: Int, type: String) {
        viewModelScope.launch {
            _sideEffects.emit(BookmarkSideEffect.NavigateToPlaceDetail(placeId, type))
        }
    }
}