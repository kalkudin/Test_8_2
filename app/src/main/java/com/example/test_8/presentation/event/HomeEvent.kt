package com.example.test_8.presentation.event

sealed class HomeEvent {
    data object GetHomeItems : HomeEvent()
}