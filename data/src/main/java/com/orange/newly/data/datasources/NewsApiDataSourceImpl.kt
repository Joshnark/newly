package com.orange.newly.data.datasources

import com.orange.newly.data.api.NewsApi
import com.orange.newly.data.errors.NetworkError
import com.orange.newly.data.models.PopularNewDto
import com.orange.newly.data.models.SearchNewDto
import com.orange.newly.data.models.TopNewDto
import com.orange.newly.domain.errors.AppError
import com.orange.newly.domain.models.Category
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import javax.inject.Inject

class NewsApiDataSourceImpl @Inject constructor(
    val api: NewsApi
): NewsDataSource {

    override suspend fun getPopularNews(): Result<List<PopularNewDto>, AppError> {
        return runCatching {
            Success(api.getPopularNews().results)
        }.getOrElse {
            Failure(NetworkError)
        }
    }

    override suspend fun getTopNews(): Result<List<TopNewDto>, AppError> {
        return runCatching {
            Success(api.getTopNews().results)
        }.getOrElse {
            Failure(NetworkError)
        }
    }

    override suspend fun getNewsByCategory(category: Category): Result<List<SearchNewDto>, AppError> {
        val deskCategory = "desk:\"${category.value}\""
        return runCatching {
            Success(api.getNewsByCategory(deskCategory).response.docs)
        }.getOrElse {
            Failure(NetworkError)
        }
    }

    override suspend fun searchNews(query: String): Result<List<SearchNewDto>, AppError> {
        return runCatching {
            Success(api.searchNews(query).response.docs)
        }.getOrElse {
            Failure(NetworkError)
        }
    }

}