package com.eoyeongbooyeong.stamp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StampViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(StampState())
    val state: StateFlow<StampState>
        get() = _state.asStateFlow()

    init {
        _state.value = StampState(
            userName = "부영이",
            stampList = persistentListOf("1", "2", "3", "4"),
            collectedStampList = persistentListOf(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10"
            ),
        )
    }
}
