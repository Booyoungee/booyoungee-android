package com.eoyeongbooyeong.new_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class NewHomeViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(NewHomeState())
    val state: StateFlow<NewHomeState>
        get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<NewHomeSideEffect>()
    val sideEffects: SharedFlow<NewHomeSideEffect>
        get() = _sideEffects.asSharedFlow()

    fun navigateToWebView(url: String) {
        viewModelScope.launch {
            _sideEffects.emit(NewHomeSideEffect.NavigateToWebView(url))
        }
    }

}
