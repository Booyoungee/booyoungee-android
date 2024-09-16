package com.eoyeongbooyeong.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
class HomeViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    init {
        getRecommendPlace()
    }

    private val _sideEffects = MutableSharedFlow<HomeSideEffect>()
    val sideEffects: SharedFlow<HomeSideEffect>
        get() = _sideEffects.asSharedFlow()

    fun navigateToWebView(url: String) {
        viewModelScope.launch {
            _sideEffects.emit(HomeSideEffect.NavigateToWebView(url))
        }
    }

    fun navigateToSearch() {
        viewModelScope.launch {
            _sideEffects.emit(HomeSideEffect.NavigateToSearch)
        }
    }

    fun navigateToPlaceDetail(placeId: Int, placeName: String) {
        viewModelScope.launch {
            _sideEffects.emit(HomeSideEffect.NavigateToPlaceDetail(placeId, placeName))
        }
    }

    fun navigateToCategoryPlace(placeType: String) {
        viewModelScope.launch {
            _sideEffects.emit(HomeSideEffect.NavigateToCategoryPlace(placeType))
        }
    }

    private fun getRecommendPlace() {
        viewModelScope.launch {
            placeRepository.getRecommendPlace()
                .onSuccess {
                    _state.value = _state.value.copy(recommendedPlace = it)
                }.onFailure(Timber::e)
        }
    }
}
