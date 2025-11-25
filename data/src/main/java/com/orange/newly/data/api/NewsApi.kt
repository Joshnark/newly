package com.orange.newly.data.api

import com.orange.newly.data.models.ApiResponse
import retrofit2.http.GET

interface NewsApi {
    @GET("everything")
    suspend fun getAllNews(): ApiResponse

    @GET("top-headlines")
    suspend fun getTopNews(): ApiResponse
}