package com.orange.newly.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orange.newly.data.models.NewEntity

@Dao
interface TopNewsDao {

    @Query("SELECT * FROM news")
    fun getPaginatedItems(): PagingSource<Int, NewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: List<NewEntity>)

    @Query("DELETE FROM news")
    suspend fun deleteAll()

}