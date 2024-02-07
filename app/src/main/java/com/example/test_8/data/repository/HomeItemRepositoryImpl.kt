package com.example.test_8.data.repository

import android.util.Log
import com.example.test_8.data.common.HandleResponse
import com.example.test_8.data.common.Resource
import com.example.test_8.data.remote.mapper.mapResource
import com.example.test_8.data.remote.mapper.toHomeItem
import com.example.test_8.data.remote.service.HomeItemService
import com.example.test_8.domain.model.HomeItem
import com.example.test_8.domain.repository.HomeItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeItemRepositoryImpl @Inject constructor(
    private val service: HomeItemService,
    private val handleResponse: HandleResponse
) : HomeItemRepository {

    override fun getHomeItems(): Flow<Resource<List<HomeItem>>> {
        Log.d("Repository", "Repository entered")
        return handleResponse.handleApiCall { service.getHomeItems() }
            .mapResource { list ->
                list.map { it.toHomeItem() }
            }
    }
}