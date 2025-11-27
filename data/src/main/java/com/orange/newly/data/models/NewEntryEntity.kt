package com.orange.newly.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.ListType

@Entity(
    tableName = "news_entries",
    primaryKeys = ["newId", "listType", "category"],
    foreignKeys = [
        ForeignKey(
            entity = NewEntity::class,
            parentColumns = ["id"],
            childColumns = ["newId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("newId"),
        Index("listType", "category", "position")
    ]
)
data class NewEntryEntity(
    val newId: String,
    val listType: ListType,
    val category: Category,
    val position: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)