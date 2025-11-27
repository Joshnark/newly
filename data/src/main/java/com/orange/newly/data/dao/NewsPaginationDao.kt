package com.orange.newly.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orange.newly.data.models.NewsPaginationStateEntity
import com.orange.newly.domain.models.Category

@Dao
interface NewsPaginationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(state: NewsPaginationStateEntity)

    @Query("SELECT * FROM news_pagination_state WHERE category = :category")
    suspend fun getByCategory(category: Category): NewsPaginationStateEntity?

    @Query("DELETE FROM news_pagination_state WHERE category = :category")
    suspend fun deleteByCategory(category: Category)

}