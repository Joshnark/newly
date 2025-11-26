package com.orange.newly.data.api

import androidx.annotation.Size
import com.orange.newly.data.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getAllNews(): ApiResponse

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): ApiResponse
}