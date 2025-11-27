package com.orange.newly.data.datasources

import com.orange.newly.data.models.PopularNewDto
import com.orange.newly.data.models.SearchNewDto
import com.orange.newly.data.models.TopNewDto
import com.orange.newly.domain.errors.AppError
import com.orange.newly.domain.models.Category
import dev.forkhandles.result4k.Result

interface NewsDataSource {
    suspend fun getPopularNews(): Result<List<PopularNewDto>, AppError>
    suspend fun getTopNews(): Result<List<TopNewDto>, AppError>
    suspend fun getNewsByCategory(category: Category): Result<List<SearchNewDto>, AppError>
    suspend fun searchNews(query: String): Result<List<SearchNewDto>, AppError>
}