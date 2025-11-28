package com.orange.newly.data.datastores

import androidx.paging.PagingSource
import com.orange.newly.data.models.NewEntity
import com.orange.newly.data.models.NewsPaginationStateEntity
import com.orange.newly.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface NewsDataStore {
    suspend fun addTopNews(news: List<NewEntity>)
    fun getTopNews(): Flow<List<NewEntity>>

    suspend fun addPopularNews(news: List<NewEntity>)
    fun getPopularNews(): Flow<List<NewEntity>>

    suspend fun addCategoryNews(news: List<NewEntity>, category: Category)

    fun getCategoryNews(category: Category): PagingSource<Int, NewEntity>

    suspend fun refreshCategoryNews(category: Category)

    suspend fun getCategoryNewsPaginationState(category: Category): NewsPaginationStateEntity?

    suspend fun insertCategoryNewsPaginationState(state: NewsPaginationStateEntity)

    suspend fun getNew(id: String): NewEntity?
}