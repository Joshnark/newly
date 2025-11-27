package com.orange.newly.data

import androidx.paging.PagingData
import com.orange.newly.data.datasources.NewsDataSource
import com.orange.newly.data.datastores.NewsDataStore
import com.orange.newly.data.mappers.toDomain
import com.orange.newly.data.mappers.toEntity
import com.orange.newly.data.paging.CategoryNewsRemoteMediator
import com.orange.newly.data.paging.SearchNewsPagingSource
import com.orange.newly.domain.NewsRepository
import com.orange.newly.domain.errors.AppError
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.asFailure
import dev.forkhandles.result4k.asSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dataSource: NewsDataSource,
    private val dataStore: NewsDataStore,
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

    override fun getTopNews(): Flow<List<New>> {
        return dataStore.getTopNews().map { it.toDomain() }
    }

    override fun getPopularNews(): Flow<List<New>> {
        return dataStore.getPopularNews().map { it.toDomain() }
    }

    override suspend fun refreshPopularNews(): Result<Unit, AppError> {
        return when(val apiResult = dataSource.getPopularNews()) {
            is Success -> dataStore.addPopularNews(apiResult.value.toEntity()).asSuccess()
            is Failure -> apiResult.reason.asFailure()
        }
    }

    override suspend fun refreshTopNews(): Result<Unit, AppError> {
        return when(val apiResult = dataSource.getTopNews()) {
            is Success -> dataStore.addTopNews(apiResult.value.toEntity()).asSuccess()
            is Failure -> apiResult.reason.asFailure()
        }
    }
}