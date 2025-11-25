package com.orange.newly.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.orange.newly.data.INITIAL_PAGE
import com.orange.newly.data.datasources.NewsDataSource
import com.orange.newly.data.datastores.NewsDataStore
import com.orange.newly.data.mappers.toEntities
import com.orange.newly.data.models.NewEntity
import com.orange.newly.domain.models.Category
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@OptIn(ExperimentalPagingApi::class)
class TopNewsRemoteMediator @AssistedInject constructor(
    private val dataSource: NewsDataSource,
    private val dataStore: NewsDataStore,
    @Assisted private val category: Category,
) : RemoteMediator<Int, NewEntity>() {

    @AssistedFactory
    interface Factory {
        fun create(category: Category): TopNewsRemoteMediator
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewEntity>
    ): MediatorResult {
        return runCatching {
            val page = when (loadType) {
                LoadType.REFRESH -> INITIAL_PAGE
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()?.let {
                        (state.pages.sumOf { it.data.size } / state.config.pageSize) + 1
                    } ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val items = dataSource.getTopNews(category, page).toEntities(category)
            dataStore.addTopNews(items, loadType == LoadType.REFRESH)

            MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        }.getOrElse { exception ->
            MediatorResult.Error(exception)
        }
    }
}