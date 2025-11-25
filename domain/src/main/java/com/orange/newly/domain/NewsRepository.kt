package com.orange.newly.domain

import androidx.paging.PagingData
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getTopNews(category: Category): Flow<PagingData<New>>
    fun searchNews(query: String): Flow<PagingData<New>>
}