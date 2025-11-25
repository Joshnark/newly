package com.orange.newly.data.datasources

import com.orange.newly.data.api.NewsApi
import com.orange.newly.data.models.NewDto
import com.orange.newly.domain.models.Category
import javax.inject.Inject

class NewsApiDataSourceImpl @Inject constructor(
    val api: NewsApi
): NewsDataSource {

    override suspend fun searchNews(query: String, page: Int): List<NewDto> {
        return api.getAllNews().articles
    }

    override suspend fun getTopNews(category: Category, page: Int): List<NewDto> {
        return api.getTopNews().articles
    }

}