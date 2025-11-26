package com.orange.newly.data.datasources

import com.orange.newly.data.models.NewDto
import com.orange.newly.domain.models.Category

interface NewsDataSource {
    suspend fun searchNews(query: String, page: Int): List<NewDto>
    suspend fun getTopNews(category: Category, page: Int, pageSize: Int): List<NewDto>
}