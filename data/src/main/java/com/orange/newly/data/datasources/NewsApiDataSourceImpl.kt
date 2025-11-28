package com.orange.newly.data.datasources

import com.orange.newly.data.api.NewsApi
import com.orange.newly.domain.exceptions.NetworkException
import com.orange.newly.domain.exceptions.TooManyRequestsException
import com.orange.newly.data.models.PopularNewDto
import com.orange.newly.data.models.SearchNewDto
import com.orange.newly.data.models.TopNewDto
import com.orange.newly.domain.models.Category
import retrofit2.HttpException
import javax.inject.Inject

class NewsApiDataSourceImpl @Inject constructor(
    val api: NewsApi
): NewsDataSource {

    override suspend fun getPopularNews(): List<PopularNewDto> {
        return runCatching {
            api.getPopularNews().results
        }.getOrElse { exception ->
            throw if (exception is HttpException && exception.code() == 429) {
                TooManyRequestsException()
            } else {
                NetworkException(exception)
            }
        }
    }

    override suspend fun getTopNews(): List<TopNewDto> {
        return runCatching {
            api.getTopNews().results
        }.getOrElse { exception ->
            throw if (exception is HttpException && exception.code() == 429) {
                TooManyRequestsException()
            } else {
                NetworkException(exception)
            }
        }
    }

    override suspend fun getNewsByCategory(category: Category, page: Int): List<SearchNewDto> {
        val deskCategory = "desk:${category.value}"
        return runCatching {
            api.getNewsByCategory(deskCategory, page).response.docs
        }.getOrElse { exception ->
            throw if (exception is HttpException && exception.code() == 429) {
                TooManyRequestsException()
            } else {
                NetworkException(exception)
            }
        }
    }

    override suspend fun searchNews(query: String, page: Int): List<SearchNewDto> {
        return runCatching {
            api.searchNews(query, page).response.docs
        }.getOrElse { exception ->
            throw if (exception is HttpException && exception.code() == 429) {
                TooManyRequestsException()
            } else {
                NetworkException(exception)
            }
        }
    }

}