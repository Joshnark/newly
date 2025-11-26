package com.orange.newly.data.api

import com.orange.newly.data.models.PopularNewsResponse
import retrofit2.http.GET

interface NewsApi {
    @GET("mostpopular/v2/viewed/1.json")
    suspend fun getPopularNews(): PopularNewsResponse
}