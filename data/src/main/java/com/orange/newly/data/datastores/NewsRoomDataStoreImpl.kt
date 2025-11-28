package com.orange.newly.data.datastores

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.orange.newly.data.AppDatabase
import com.orange.newly.data.dao.NewsDao
import com.orange.newly.data.dao.NewsEntriesDao
import com.orange.newly.data.dao.NewsPaginationDao
import com.orange.newly.data.models.NewEntity
import com.orange.newly.data.models.NewEntryEntity
import com.orange.newly.data.models.NewsPaginationStateEntity
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.ListType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRoomDataStoreImpl @Inject constructor(
    private val database: AppDatabase,
    private val newsDao: NewsDao,
    private val newsEntriesDao: NewsEntriesDao,
    private val newsPaginationDao: NewsPaginationDao
): NewsDataStore {
    override suspend fun addPopularNews(news: List<NewEntity>) {
        addNewAndEntry(news, ListType.POPULAR, Category.HOME)
    }

    override fun getPopularNews(): Flow<List<NewEntity>> {
        return newsDao
            .getPopularNews()
    }

    override suspend fun addTopNews(news: List<NewEntity>) {
        addNewAndEntry(news, ListType.TOP, Category.HOME)
    }

    override fun getTopNews(): Flow<List<NewEntity>> {
        return newsDao.getTopNews()
    }

    override suspend fun addCategoryNews(news: List<NewEntity>, category: Category) {
        addNewAndEntry(news, ListType.CATEGORY, category)
    }

    override fun getCategoryNews(category: Category): PagingSource<Int, NewEntity> {
        return newsDao.getCategoryNews(category)
    }

    override suspend fun refreshCategoryNews(category: Category) {
        database.withTransaction {
            newsEntriesDao.deleteAll(listType = ListType.CATEGORY, category)
            newsPaginationDao.deleteByCategory(category)
            newsDao.clean()
        }
    }

    override suspend fun getCategoryNewsPaginationState(category: Category): NewsPaginationStateEntity? {
        return newsPaginationDao.getByCategory(category)
    }

    override suspend fun insertCategoryNewsPaginationState(state: NewsPaginationStateEntity) {
        newsPaginationDao.insert(state)
    }

    override suspend fun getNew(id: String): NewEntity? {
        return newsDao.getById(id)
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