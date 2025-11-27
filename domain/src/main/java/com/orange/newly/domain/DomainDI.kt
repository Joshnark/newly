package com.orange.newly.domain

import com.orange.newly.domain.usecases.GetPopularNewsUseCase
import com.orange.newly.domain.usecases.GetTopNewsUseCase
import com.orange.newly.domain.usecases.RefreshPopularNewsUseCase
import com.orange.newly.domain.usecases.RefreshTopNewsUseCase
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
    fun provideGetPopularNewsUseCase(repository: NewsRepository) = GetPopularNewsUseCase(repository)

    @Provides
    fun provideRefreshPopularNews(repository: NewsRepository) = RefreshPopularNewsUseCase(repository)

    @Provides
    fun provideRefreshTopNews(repository: NewsRepository) = RefreshTopNewsUseCase(repository)

    @Provides
    fun provideSearchNewsUseCase(repository: NewsRepository) = SearchNewsUseCase(repository)

}