package com.orange.newly.data.di

import com.orange.newly.data.NewsRepositoryImpl
import com.orange.newly.data.datasources.NewsApiDataSourceImpl
import com.orange.newly.data.datasources.NewsDataSource
import com.orange.newly.data.datastores.NewsDataStore
import com.orange.newly.data.datastores.NewsRoomDataStoreImpl
import com.orange.newly.domain.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataStore(newsRoomDataStoreImpl: NewsRoomDataStoreImpl): NewsDataStore

    @Binds
    abstract fun bindDataSource(newsApiDataSourceImpl: NewsApiDataSourceImpl): NewsDataSource

    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}