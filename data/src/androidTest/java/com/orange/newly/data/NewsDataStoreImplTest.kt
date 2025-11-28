package com.orange.newly.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.orange.newly.data.datastores.NewsRoomDataStoreImpl
import com.orange.newly.data.models.NewEntityMother
import com.orange.newly.domain.models.Category
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class NewsDataStoreImplTest {

    private lateinit var database: AppDatabase
    private lateinit var sut: NewsRoomDataStoreImpl

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        sut = NewsRoomDataStoreImpl(
            database = database,
            newsDao = database.getNewsDao(),
            newsEntriesDao = database.getNewsEntriesDao(),
            newsPaginationDao = database.getNewsPaginationDao()
        )
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun givenPopNews_WhenInsert_ThenFetchPagedData() = runTest {
        val news = NewEntityMother.list

        sut.addPopularNews(news)

        sut.getPopularNews().test {
            val result = awaitItem()
            assertEquals(news.size, result.size)
            assertEquals(news.first().id, result.first().id)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun givenTopNews_WhenInsert_ThenFetchPagedData() = runTest {
        val news = NewEntityMother.list

        sut.addTopNews(news)

        sut.getTopNews().test {
            val result = awaitItem()
            assertEquals(news.size, result.size)
            assertEquals(news.first().id, result.first().id)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun refreshCategoryNews_deleterOnlyThatCategory() = runTest {
        val techNew = NewEntityMother.single.copy(id = "tech-1")
        val scienceNew = NewEntityMother.single.copy(id = "science-1")

        sut.addCategoryNews(listOf(techNew), Category.TECH)
        sut.addCategoryNews(listOf(scienceNew), Category.SCIENCE)

        sut.refreshCategoryNews(Category.TECH)

        val resultTechNew = sut.getNew(techNew.id)
        val resultScienceNew = sut.getNew(scienceNew.id)

        assertEquals(resultScienceNew, scienceNew)
        assertNull(resultTechNew)
    }

}