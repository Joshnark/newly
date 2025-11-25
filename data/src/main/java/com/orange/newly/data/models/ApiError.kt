package com.orange.newly.data.models

data class ApiError(
    val status: String,
    val code: String,
    val message: String
)