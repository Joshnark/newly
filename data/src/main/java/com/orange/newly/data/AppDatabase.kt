package com.orange.newly.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.orange.newly.data.dao.NewsDao
import com.orange.newly.data.dao.NewsEntriesDao
import com.orange.newly.data.dao.NewsPaginationDao
import com.orange.newly.data.models.NewEntity
import com.orange.newly.data.models.NewEntryEntity
import com.orange.newly.data.models.NewsPaginationStateEntity

@Database(
    entities = [NewEntity::class, NewEntryEntity::class, NewsPaginationStateEntity::class],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
    abstract fun getNewsEntriesDao(): NewsEntriesDao
    abstract fun getNewsPaginationDao(): NewsPaginationDao

    companion object {
        fun build(context: Context, name: String): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                name
            ).fallbackToDestructiveMigration(true).build()
        }
    }
}