package com.orange.newly.data.datasources

import com.orange.newly.data.models.PopularNewDto
import com.orange.newly.data.models.SearchNewDto
import com.orange.newly.data.models.TopNewDto
import com.orange.newly.domain.errors.AppError
import com.orange.newly.domain.models.Category
import dev.forkhandles.result4k.Result

interface NewsDataSource {
    suspend fun getPopularNews(): List<PopularNewDto>
    suspend fun getTopNews(): List<TopNewDto>
    suspend fun getNewsByCategory(category: Category, page: Int): List<SearchNewDto>
    suspend fun searchNews(query: String, page: Int): List<SearchNewDto>
}