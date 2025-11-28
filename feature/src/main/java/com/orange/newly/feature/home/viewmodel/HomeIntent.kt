package com.orange.newly.feature.home.viewmodel

import com.orange.newly.domain.models.Category

sealed interface HomeIntent {
    data class LoadCategory(val category: Category): HomeIntent
}