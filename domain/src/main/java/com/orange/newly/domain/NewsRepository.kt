package com.orange.newly.domain

import androidx.paging.PagingData
import com.orange.newly.domain.errors.AppError
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import dev.forkhandles.result4k.Result
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun searchNews(query: String): Flow<PagingData<New>>
    fun getNewsByCategory(category: Category): Flow<PagingData<New>>

    fun getTopNews(): Flow<List<New>>
    fun getPopularNews(): Flow<List<New>>

    suspend fun getNewById(id: String): Result<New, AppError>


    suspend fun refreshTopNews(): Result<Unit, AppError>
    suspend fun refreshPopularNews(): Result<Unit, AppError>
}