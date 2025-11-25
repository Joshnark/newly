package com.orange.newly.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orange.newly.data.NEWS_TABLE_NAME
import com.orange.newly.domain.models.Category

@Entity(tableName = NEWS_TABLE_NAME)
data class NewEntity(
    @PrimaryKey val title: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val url: String,
    val urlToImage: String,
    val category: Category?
)