package com.orange.newly.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orange.newly.data.INITIAL_PAGE
import com.orange.newly.data.NYTIMES_LIMIT_PAGE
import com.orange.newly.data.datasources.NewsDataSource
import com.orange.newly.data.models.PopularNewDto
import com.orange.newly.data.models.SearchNewDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SearchNewsPagingSource @AssistedInject constructor(
    private val dataSource: NewsDataSource,
    @Assisted private val query: String
): PagingSource<Int, SearchNewDto>() {

    @AssistedFactory
    interface Factory {
        fun create(query: String): SearchNewsPagingSource
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SearchNewDto> {
        return runCatching {
            val page = params.key ?: INITIAL_PAGE
            val items = dataSource.searchNews(query, page)

            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if ( page >= NYTIMES_LIMIT_PAGE) null else page.inc()
            )
        }.getOrElse { exception ->
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchNewDto>): Int? {
        return state.anchorPosition
    }

}