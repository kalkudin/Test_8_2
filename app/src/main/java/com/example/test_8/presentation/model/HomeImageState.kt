package com.example.test_8.presentation.model

data class HomeImageState(
    val isLoading: Boolean = false,
    val isSuccess: List<HomeImage>? = null,
    val errorMessage: String? = null
)
