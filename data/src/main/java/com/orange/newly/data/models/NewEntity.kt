package com.orange.newly.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orange.newly.data.NEWS_TABLE_NAME

@Entity(
    tableName = NEWS_TABLE_NAME,
)
data class NewEntity(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val url: String,
    val urlToImage: String,
    val source: String? = null,
)