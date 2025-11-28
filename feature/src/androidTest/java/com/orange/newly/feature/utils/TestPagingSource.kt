package com.orange.newly.feature.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState

class TestPagingSource <T: Any>(
    private val data: List<T>
): PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, T>) = null

}