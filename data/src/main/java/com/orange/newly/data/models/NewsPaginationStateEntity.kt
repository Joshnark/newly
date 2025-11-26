package com.orange.newly.data.models

import androidx.room.Entity
import com.orange.newly.domain.models.Category

@Entity(
    tableName = "news_pagination_state"
)
data class NewsPaginationStateEntity(
    val category: Category,
    val currentPage: Int,
    val nextPage: Int?,
    val lastUpdated: Long = System.currentTimeMillis()
)