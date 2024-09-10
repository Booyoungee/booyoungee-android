package com.eoyeongbooyeong.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SearchSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        getHotPlace()
    }

    private fun getHotPlace() {
        viewModelScope.launch {
            placeRepository.getHotPlace()
                .onSuccess {
                    _state.value = _state.value.copy(
                        hotTravelDestinations = it.toImmutableList(),
                        hotTravelDestinationsFetchTime = it.first().updatedAt.apply {
                            this.substring(0, 10).split("-").let { (year, month, day) ->
                                LocalDateTime.of(year.toInt(), month.toInt(), day.toInt(), 8, 0)
                                    .toString()
                            }
                        },
                    )

                    Log.e("TAG", "getHotPlace: ${state.value.hotTravelDestinationsFetchTime}")
                }.onFailure(Timber::e)
        }
    }

    fun navigateUp() {
        viewModelScope.launch {
            _sideEffect.emit(SearchSideEffect.NavigateUp)
        }
    }
}
