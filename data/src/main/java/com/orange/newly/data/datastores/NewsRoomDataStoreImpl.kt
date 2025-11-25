package com.orange.newly.data.datastores

import androidx.paging.PagingSource
import com.orange.newly.data.dao.TopNewsDao
import com.orange.newly.data.models.NewEntity
import com.orange.newly.domain.models.Category
import javax.inject.Inject

class NewsRoomDataStoreImpl @Inject constructor(
    private val newsDao: TopNewsDao
): NewsDataStore {
    override suspend fun addTopNews(news: List<NewEntity>, isRefresh: Boolean) {
        if (isRefresh) {
            newsDao.deleteAll()
        }

        newsDao.insert(news)
    }

    override fun getTopNews(category: Category) = newsDao.getPaginatedItems()
}