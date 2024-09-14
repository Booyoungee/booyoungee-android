package com.eoyeongbooyeong.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.MovieRepository
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import com.eoyeongbooyeong.domain.repository.TourInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    private val tourInfoRepository: TourInfoRepository,
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String>
        get() = _query.asStateFlow()

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SearchSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        getHotPlace()

        viewModelScope.launch {
            _query.debounce(DEBOUNCE_DURATION)
                .collectLatest { debounced ->
                    if (debounced.isNotBlank()) {
                        searchOnKeyword(debounced)
                    }
                }
        }
    }

    private fun getHotPlace() {
        viewModelScope.launch {
            placeRepository.getHotPlace()
                .onSuccess {
                    _state.value = _state.value.copy(
                        hotTravelDestinations = it.toImmutableList(),
                        hotTravelDestinationsFetchTime = it.first().updatedAt.run {
                            this.substring(0, 10).split("-").let { (year, month, day) ->
                                "${year}년 ${month}월 ${day}일 ${this.substring(11, 16)} 기준"
                            }
                        },
                    )

                }.onFailure(Timber::e)
        }
    }

    fun clickHotPlace(query: String) {
        viewModelScope.launch {
            _query.value = query
        }
        searchOnKeyword(query)
    }

    private fun searchOnKeyword(query: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            movieRepository.searchOnKeyword(
                numOfRows = 10,
                pageNo = 1,
                keyword = query,
            ).onSuccess {
                _state.value = _state.value.copy(
                    searchResults = it.map { it.toPlaceDetailsEntity() }
                        .toImmutableList(),
                    isLoading = false,
                )
            }.onFailure(Timber::e)
        }
    }

    fun queryValueChanged(query: String) {
        viewModelScope.launch {
            _query.value = query
        }
    }

    fun navigateUp() {
        viewModelScope.launch {
            _sideEffect.emit(SearchSideEffect.NavigateUp)
        }
    }

    fun navigateToPlaceDetail(placeId: Int, type: String) {
        viewModelScope.launch {
            _sideEffect.emit(SearchSideEffect.NavigateToPlaceDetail(placeId, type))
        }
    }

    companion object {
        private const val DEBOUNCE_DURATION: Long = 300
    }
}
