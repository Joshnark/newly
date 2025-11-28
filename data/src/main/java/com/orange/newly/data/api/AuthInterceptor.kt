package com.orange.newly.data.api

import com.orange.newly.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = BuildConfig.API_TOKEN

        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api-key",token).build();
        val authenticatedRequest = request.newBuilder().url(url).build()

        return chain.proceed(authenticatedRequest)
    }
}