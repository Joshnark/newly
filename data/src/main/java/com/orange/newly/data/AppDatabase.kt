package com.orange.newly.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.orange.newly.data.dao.TopNewsDao
import com.orange.newly.data.models.NewEntity

@Database(
    entities = [NewEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getNewsDao(): TopNewsDao

    companion object {
        fun build(context: Context, name: String): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                name
            ).build()
        }
    }
}