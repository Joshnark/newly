package com.orange.newly.domain

import androidx.paging.PagingData
import com.orange.newly.domain.errors.NewlyError
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import dev.forkhandles.result4k.Result
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun searchNews(query: String): Flow<PagingData<New>>
    fun getNewsByCategory(category: Category): Flow<PagingData<New>>

    suspend fun getRecommended(): Result<List<New>, NewlyError>
    suspend fun getPopularNews(): Result<List<New>, NewlyError>
}