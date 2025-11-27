package com.orange.newly.data.datastores

import androidx.room.withTransaction
import com.orange.newly.data.AppDatabase
import com.orange.newly.data.dao.NewsDao
import com.orange.newly.data.dao.NewsEntriesDao
import com.orange.newly.data.models.NewEntity
import com.orange.newly.data.models.NewEntryEntity
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.ListType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRoomDataStoreImpl @Inject constructor(
    private val database: AppDatabase,
    private val newsDao: NewsDao,
    private val newsEntriesDao: NewsEntriesDao
): NewsDataStore {
//    override suspend fun addTopNews(news: List<NewEntity>, isRefresh: Boolean) {
//        if (isRefresh) {
//            newsDao.deleteAll()
//        }
//
//        newsDao.insert(news)
//    }
//
//    override fun getTopNews(category: Category) = newsDao.getPaginatedItems()
//
//    override suspend fun getLastPage(): Int? {
//        return newsDao.getLastPage()
//    }


    override suspend fun addPopularNews(news: List<NewEntity>) {
        return addNewAndEntry(news, ListType.POPULAR, Category.HOME)
    }

    override fun getPopularNews(): Flow<List<NewEntity>> {
        return newsDao
            .getPopularNews()
    }

    override suspend fun addTopNews(news: List<NewEntity>) {
        return addNewAndEntry(news, ListType.TOP, Category.HOME)
    }

    override fun getTopNews(): Flow<List<NewEntity>> {
        return newsDao.getTopNews()
    }

    private suspend fun addNewAndEntry(news: List<NewEntity>, listType: ListType, category: Category) {
        database.withTransaction {
            newsDao.insert(news)
            newsEntriesDao.insert(
                entries = news.mapIndexed { index, entity ->
                    NewEntryEntity(
                        newId = entity.id,
                        listType = listType,
                        category = category,
                        position = index
                    )
                }
            )
        }
    }

}