package com.orange.newly.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orange.newly.data.datasources.NewsDataSource
import com.orange.newly.data.datastores.NewsDataStore
import com.orange.newly.data.errors.NetworkError
import com.orange.newly.data.mappers.toDomain
import com.orange.newly.data.paging.SearchNewsPagingSource
import com.orange.newly.data.paging.TopNewsRemoteMediator
import com.orange.newly.domain.NewsRepository
import com.orange.newly.domain.errors.NewlyError
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.asSuccess
import dev.forkhandles.result4k.failureOrNull
import dev.forkhandles.result4k.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dataSource: NewsDataSource,
    private val dataStore: NewsDataStore,
    private val topNewsRemoteMediatorFactory: TopNewsRemoteMediator.Factory,
    private val searchNewsPagingSourceFactory: SearchNewsPagingSource.Factory
): NewsRepository {

//    @OptIn(ExperimentalPagingApi::class)
//    override fun getSectionNews(category: Category): Flow<PagingData<New>> {
//        return Pager(
//            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
//            remoteMediator = topNewsRemoteMediatorFactory.create(category),
//            pagingSourceFactory = { dataStore.getTopNews(category) }
//        ).flow.map { data -> data.toDomain() }
//    }

    override fun searchNews(query: String): Flow<PagingData<New>> {
//        return Pager(
//            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
//            pagingSourceFactory = {
//                searchNewsPagingSourceFactory.create(query)
//            }
//        ).flow.map { data -> data.toDomain() }

        return flowOf(PagingData.empty())
    }

    override fun getNewsByCategory(category: Category): Flow<PagingData<New>> {
        return flowOf(PagingData.empty())
    }

    override suspend fun getRecommended(): Result<List<New>, NewlyError> {
        return Success(emptyList())
    }

    override suspend fun getPopularNews(): Result<List<New>, NewlyError> {
        val apiResult = dataSource.getPopularNews()

        if (apiResult is Failure) {
            return Success(emptyList())
        } else {
            // save the thing
        }

        return apiResult.map { it.toDomain() }
    }
}