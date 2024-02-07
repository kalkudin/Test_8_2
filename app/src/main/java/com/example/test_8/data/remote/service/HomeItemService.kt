package com.example.test_8.data.remote.service

import com.example.test_8.data.remote.dto.HomeItemDto
import retrofit2.Response
import retrofit2.http.GET

interface HomeItemService {
    @GET("/v3/0545ddc1-c487-46ce-b70c-5b95270d6b76")
    suspend fun getHomeItems() : Response<List<HomeItemDto>>
}