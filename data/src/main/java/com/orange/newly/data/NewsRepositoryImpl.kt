package com.orange.newly.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orange.newly.data.datastores.NewsDataStore
import com.orange.newly.data.mappers.toDomain
import com.orange.newly.data.paging.SearchNewsPagingSource
import com.orange.newly.data.paging.TopNewsRemoteMediator
import com.orange.newly.domain.NewsRepository
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dataStore: NewsDataStore,
    private val topNewsRemoteMediatorFactory: TopNewsRemoteMediator.Factory,
    private val searchNewsPagingSourceFactory: SearchNewsPagingSource.Factory
): NewsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopNews(category: Category): Flow<PagingData<New>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            remoteMediator = topNewsRemoteMediatorFactory.create(category),
            pagingSourceFactory = { dataStore.getTopNews(category) }
        ).flow.map { data -> data.toDomain() }
    }

    override fun searchNews(query: String): Flow<PagingData<New>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                searchNewsPagingSourceFactory.create(query)
            }
        ).flow.map { data -> data.toDomain() }
    }
}