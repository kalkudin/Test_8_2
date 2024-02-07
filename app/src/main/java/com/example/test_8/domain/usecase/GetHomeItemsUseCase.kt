package com.example.test_8.domain.usecase

import com.example.test_8.data.common.Resource
import com.example.test_8.domain.model.HomeItem
import com.example.test_8.domain.repository.HomeItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeItemsUseCase @Inject constructor(private val repository: HomeItemRepository) {
    operator fun invoke(): Flow<Resource<List<HomeItem>>> {
        return repository.getHomeItems()
    }
}