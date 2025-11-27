package com.orange.newly.feature.foryou.viewmodel

import com.orange.newly.domain.errors.AppError
import com.orange.newly.domain.models.New

data class ForYouUIState(
    val topNews: List<New> = emptyList(),
    val popularNews: List<New> = emptyList(),
    val errorLoadingTopNews: AppError? = null,
    val errorLoadingPopularNews: AppError? = null,
    val refreshError: AppError? = null,
    val isLoading: Boolean = false
)