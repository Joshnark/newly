package com.orange.newly.data.datastores

import androidx.paging.PagingSource
import com.orange.newly.data.models.NewEntity
import com.orange.newly.domain.models.Category

interface NewsDataStore {
    suspend fun addTopNews(news: List<NewEntity>, isRefresh: Boolean)
    suspend fun getLastPage(): Int?
    fun getTopNews(category: Category): PagingSource<Int, NewEntity>
}