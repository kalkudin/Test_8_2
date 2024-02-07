package com.example.test_8.domain.repository

import com.example.test_8.data.common.Resource
import com.example.test_8.domain.model.HomeItem
import kotlinx.coroutines.flow.Flow

interface HomeItemRepository {
    fun getHomeItems() : Flow<Resource<List<HomeItem>>>
}