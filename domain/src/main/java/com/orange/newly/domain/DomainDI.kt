package com.orange.newly.domain

import com.orange.newly.domain.usecases.GetTopNewsUseCase
import com.orange.newly.domain.usecases.SearchNewsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainDI {

    @Provides
    fun provideGetTopNewsUseCase(repository: NewsRepository) = GetTopNewsUseCase(repository)

    @Provides
    fun provideSearchNewsUseCase(repository: NewsRepository) = GetTopNewsUseCase(repository)

}