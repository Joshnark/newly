package com.orange.newly.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orange.newly.data.models.NewEntity
import com.orange.newly.domain.models.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: List<NewEntity>)

    @Query("SELECT * FROM news WHERE id = :id")
    suspend fun getById(id: String): NewEntity?

    @Query("""
        SELECT news.* from news
        INNER JOIN news_entries ON news.id = news_entries.newId
        WHERE news_entries.listType = 'POPULAR'
        ORDER BY news_entries.position ASC
    """)
    fun getPopularNews(): Flow<List<NewEntity>>

    @Query("""
        SELECT news.* from news
        INNER JOIN news_entries ON news.id = news_entries.newId
        WHERE news_entries.listType = 'TOP'
        ORDER BY news_entries.position ASC
    """)
    fun getTopNews(): Flow<List<NewEntity>>


    @Query("""
        SELECT news.* from news
        INNER JOIN news_entries ON news.id = news_entries.newId
        WHERE news_entries.listType = 'CATEGORY'
        AND news_entries.category = :category
        ORDER BY news_entries.position ASC
    """)
    fun getCategoryNews(category: Category): PagingSource<Int, NewEntity>

    @Query("""
        DELETE FROM news WHERE id NOT IN (
            SELECT DISTINCT newId FROM news_entries
        )
    """)
    fun clean()

}