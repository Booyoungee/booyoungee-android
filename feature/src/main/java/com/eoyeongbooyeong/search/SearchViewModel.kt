package com.eoyeongbooyeong.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eoyeongbooyeong.domain.entity.PlaceDetailsEntity
import com.eoyeongbooyeong.domain.repository.MovieRepository
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import com.eoyeongbooyeong.domain.repository.TourInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    private val tourInfoRepository: TourInfoRepository,
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SearchSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    private val _searchTrigger = MutableSharedFlow<Unit>()

    val searchResults: Flow<PagingData<PlaceDetailsEntity>> = _searchTrigger
        .flatMapLatest {
            tourInfoRepository.searchOnKeyword(
                numOfRows = 10,
                pageNo = 1,
                keyword = query.value,
            )
        }
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    init {
        getHotPlace()
        setupSearch()
        observeTotalCountAndLoading()
    }

    private fun setupSearch() {
        viewModelScope.launch {
            _query
                .debounce(DEBOUNCE_DURATION)
                .filter { it.isNotBlank() }
                .collectLatest { searchOnKeyword() }
        }
    }

    private fun observeTotalCountAndLoading() {
        viewModelScope.launch {
            combine(
                tourInfoRepository.getTotalCount(),
                tourInfoRepository.getIsLoading(),
                searchResults.map { }
            ) { totalCount, isLoading, _ ->
                _state.update {
                    it.copy(
                        totalCount = totalCount,
                        isPagingLoading = isLoading,
                        isLoading = false
                    )
                }
            }.collect(
                collector = {

                },
            )
        }
    }

    private fun getHotPlace() {
        viewModelScope.launch {
            placeRepository.getHotPlace()
                .onSuccess { hotPlaces ->
                    _state.update { state ->
                        state.copy(
                            hotTravelDestinations = hotPlaces.toImmutableList(),
                            hotTravelDestinationsFetchTime = hotPlaces.firstOrNull()?.updatedAt?.let { date ->
                                date.substring(0, 10).split("-").let { (year, month, day) ->
                                    "${year}년 ${month}월 ${day}일 ${date.substring(11, 16)} 기준"
                                }
                            } ?: ""
                        )
                    }
                }.onFailure(Timber::e)
        }
    }

    fun clickHotPlace(query: String) {
        _query.value = query
        searchOnKeyword()
    }

    private fun searchOnKeyword() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _searchTrigger.emit(Unit)
        }
    }

    fun queryValueChanged(query: String) {
        _query.value = query
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
        private const val DEBOUNCE_DURATION: Long = 300L
    }
}