package com.orange.newly.data.models

data class ApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewDto>
)