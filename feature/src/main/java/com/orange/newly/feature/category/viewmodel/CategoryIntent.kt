package com.orange.newly.feature.category.viewmodel

import com.orange.newly.domain.models.Category

sealed interface CategoryIntent {
    data class LoadCategory(val category: Category): CategoryIntent
}