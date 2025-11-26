package com.orange.newly.data.datasources

import com.orange.newly.data.api.NewsApi
import com.orange.newly.data.errors.NetworkError
import com.orange.newly.data.models.PopularNewDto
import com.orange.newly.domain.errors.NewlyError
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.asResultOr
import javax.inject.Inject

class NewsApiDataSourceImpl @Inject constructor(
    val api: NewsApi
): NewsDataSource {

    override suspend fun getPopularNews(): Result<List<PopularNewDto>, NewlyError> {
        return api.getPopularNews().results.asResultOr { NetworkError }
    }

}