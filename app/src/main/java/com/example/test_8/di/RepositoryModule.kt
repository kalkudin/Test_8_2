package com.example.test_8.di

import com.example.test_8.data.common.HandleResponse
import com.example.test_8.data.remote.service.HomeItemService
import com.example.test_8.data.repository.HomeItemRepositoryImpl
import com.example.test_8.domain.repository.HomeItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideHomeItemRepository(homeItemService: HomeItemService, handleResponse: HandleResponse) : HomeItemRepository {
        return HomeItemRepositoryImpl(service = homeItemService, handleResponse = handleResponse)
    }
}