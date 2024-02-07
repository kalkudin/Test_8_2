package com.example.test_8.data.remote.mapper

import com.example.test_8.data.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, R> Flow<Resource<T>>.mapResource(transform: (T) -> R): Flow<Resource<R>> = this.map { resource ->
    when (resource) {
        is Resource.Success -> Resource.Success(transform(resource.data))
        is Resource.Error -> Resource.Error(resource.errorMessage)
        is Resource.Loading -> Resource.Loading
    }
}