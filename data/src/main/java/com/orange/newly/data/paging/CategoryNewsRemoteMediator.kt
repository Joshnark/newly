package com.orange.newly.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.orange.newly.data.INITIAL_PAGE
import com.orange.newly.data.NYTIMES_LIMIT_PAGE
import com.orange.newly.data.datasources.NewsDataSource
import com.orange.newly.data.datastores.NewsDataStore
import com.orange.newly.data.mappers.toEntity
import com.orange.newly.data.models.NewEntity
import com.orange.newly.data.models.NewsPaginationStateEntity
import com.orange.newly.domain.models.Category
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.resultFrom

@OptIn(ExperimentalPagingApi::class)
class CategoryNewsRemoteMediator @AssistedInject constructor(
    private val dataSource: NewsDataSource,
    private val dataStore: NewsDataStore,
    @Assisted private val category: Category,
) : RemoteMediator<Int, NewEntity>() {

    @AssistedFactory
    interface Factory {
        fun create(category: Category): CategoryNewsRemoteMediator
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewEntity>
    ): MediatorResult {
        return runCatching {
            val page = when (loadType) {
                LoadType.REFRESH -> INITIAL_PAGE
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = false)
                LoadType.APPEND -> {
                    val pageState = dataStore.getCategoryNewsPaginationState(category)
                    pageState?.currentPage?.plus(1) ?: INITIAL_PAGE
                }
            }

            val result = resultFrom { dataSource.getNewsByCategory(category, page) }

            when(result) {
                is Failure -> {
                    MediatorResult.Error(result.reason)
                }
                is Success -> {
                    if (loadType == LoadType.REFRESH) {
                        dataStore.refreshCategoryNews(category)
                    }

                    dataStore.addCategoryNews(result.value.toEntity(), category)

                    dataStore.insertCategoryNewsPaginationState(
                        NewsPaginationStateEntity(
                            category = category,
                            currentPage = page
                        )
                    )

                    MediatorResult.Success(endOfPaginationReached = page >= NYTIMES_LIMIT_PAGE)
                }
            }
        }.getOrElse { exception ->
            MediatorResult.Error(exception)
        }
    }
}