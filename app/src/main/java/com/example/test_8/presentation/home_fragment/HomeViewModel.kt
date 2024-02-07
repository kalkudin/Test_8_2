package com.example.test_8.presentation.home_fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_8.data.common.Resource
import com.example.test_8.domain.usecase.GetHomeItemsUseCase
import com.example.test_8.presentation.event.HomeEvent
import com.example.test_8.presentation.mapper.toHomeImage
import com.example.test_8.presentation.model.HomeImageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getHomeItemsUseCase: GetHomeItemsUseCase) :
    ViewModel() {

    private val _homeItemState = MutableStateFlow(HomeImageState())
    val homeItemState: StateFlow<HomeImageState> = _homeItemState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetHomeItems -> getHomeItems()
        }
    }

    private fun getHomeItems() {
        viewModelScope.launch {
            getHomeItemsUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        Log.d("HomeViewModel", "Loading home items")
                        _homeItemState.value = _homeItemState.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        Log.d(
                            "HomeViewModel",
                            "Successfully fetched home items: ${result.data.size} items retrieved"
                        )
                        val homeImages = result.data.map { it.toHomeImage() }
                        homeImages.forEach { homeImage ->
                            Log.d(
                                "HomeViewModel",
                                "Item: ${homeImage.title}, Location: ${homeImage.location}, Rate: ${homeImage.rate}"
                            )
                        }
                        _homeItemState.value = _homeItemState.value.copy(
                            isLoading = false,
                            isSuccess = homeImages,
                            errorMessage = null
                        )
                    }

                    is Resource.Error -> {
                        Log.e("HomeViewModel", "Error fetching home items: ${result.errorMessage}")
                        _homeItemState.value = _homeItemState.value.copy(
                            isLoading = false,
                            errorMessage = result.errorMessage
                        )
                    }
                }
            }
        }
    }
}