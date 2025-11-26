package com.orange.newly.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "PMWMTmmyxLENvwSH8ThdgBST46fGxahA"

        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api-key",token).build();
        val authenticatedRequest = request.newBuilder().url(url).build()

        return chain.proceed(authenticatedRequest)
    }
}