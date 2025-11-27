package com.orange.newly.data.datastores

import com.orange.newly.data.models.NewEntity
import kotlinx.coroutines.flow.Flow

interface NewsDataStore {
    suspend fun addTopNews(news: List<NewEntity>)
    fun getTopNews(): Flow<List<NewEntity>>

    suspend fun addPopularNews(news: List<NewEntity>)
    fun getPopularNews(): Flow<List<NewEntity>>
}