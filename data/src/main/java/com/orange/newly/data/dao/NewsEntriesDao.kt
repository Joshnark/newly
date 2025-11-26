package com.orange.newly.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orange.newly.data.models.NewEntryEntity
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.ListType

@Dao
interface NewsEntriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entries: List<NewEntryEntity>)

    @Query("""
        DELETE FROM news_entries
        WHERE listType = :listType
        AND (:category IS NULL OR category = :category)
    """)
    suspend fun deleteAll(listType: ListType, category: Category)


}