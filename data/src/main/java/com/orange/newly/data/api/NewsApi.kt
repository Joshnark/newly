package com.orange.newly.data.api

import com.orange.newly.data.models.PopularNewsResponse
import com.orange.newly.data.models.SearchNewsResponse
import com.orange.newly.data.models.TopNewsResponse
import com.orange.newly.domain.models.Category
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("mostpopular/v2/viewed/1.json")
    suspend fun getPopularNews(): PopularNewsResponse

    @GET("topstories/v2/home.json")
    suspend fun getTopNews(): TopNewsResponse

    @GET("search/v2/articlesearch.json")
    suspend fun getNewsByCategory(
        @Query("fq") desk: String,
        @Query("page") page: Int
    ): SearchNewsResponse

    @GET("search/v2/articlesearch.json")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int
    ): SearchNewsResponse
}