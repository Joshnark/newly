package com.orange.newly.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orange.newly.data.INITIAL_PAGE
import com.orange.newly.data.datasources.NewsDataSource
import com.orange.newly.data.models.NewDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SearchNewsPagingSource @AssistedInject constructor(
    private val dataSource: NewsDataSource,
    @Assisted private val query: String
): PagingSource<Int, NewDto>() {

    @AssistedFactory
    interface Factory {
        fun create(query: String): SearchNewsPagingSource
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, NewDto> {
        return runCatching {
            val page = params.key ?: INITIAL_PAGE
            val items = dataSource.searchNews(query, page)

            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = if (items.isEmpty()) page.inc() else null
            )
        }.getOrElse { exception ->
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewDto>): Int? {
        return state.anchorPosition
    }

}