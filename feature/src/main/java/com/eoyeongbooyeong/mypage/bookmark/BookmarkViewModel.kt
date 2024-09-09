package com.eoyeongbooyeong.mypage.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(BookmarkState())
    val state: StateFlow<BookmarkState>
        get() = _state.asStateFlow()

    fun getBookmarkList() {
        viewModelScope.launch {
            bookmarkRepository.getBookmarkList().onSuccess {
                _state.value = _state.value.copy(myBookmarkList = it.toImmutableList())
            }.onFailure(Timber::e)
        }
    }
}