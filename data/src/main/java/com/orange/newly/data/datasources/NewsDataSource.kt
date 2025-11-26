package com.orange.newly.data.datasources

import com.orange.newly.data.models.PopularNewDto
import com.orange.newly.domain.errors.NewlyError
import com.orange.newly.domain.models.Category
import dev.forkhandles.result4k.Result

interface NewsDataSource {
    suspend fun getPopularNews(): Result<List<PopularNewDto>, NewlyError>
    suspend fun getCategoryNews(category: Category): Result<List<PopularNewDto>, NewlyError>
    suspend fun getRecommendedNews(): Result<List<PopularNewDto>, NewlyError>
    suspend fun searchNews(query: String): Result<List<PopularNewDto>, NewlyError>
}