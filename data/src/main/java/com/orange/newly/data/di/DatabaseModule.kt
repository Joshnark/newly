package com.orange.newly.data.di

import android.content.Context
import com.orange.newly.data.AppDatabase
import com.orange.newly.data.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class StorageInfo

    @StorageInfo
    @Provides
    fun name(@ApplicationContext appContext: Context): String {
        return appContext.packageName + "-storage"
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, @StorageInfo name: String): AppDatabase {
        return AppDatabase.build(context, "$name.db")
    }

    @Provides
    @Singleton
    fun provideTopNewsDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.getNewsDao()
    }

}