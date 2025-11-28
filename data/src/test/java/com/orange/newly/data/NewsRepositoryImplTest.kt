package com.orange.newly.data

import android.util.Base64
import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.orange.newly.data.datasources.NewsDataSource
import com.orange.newly.data.datastores.NewsDataStore
import com.orange.newly.data.mappers.toEntity
import com.orange.newly.data.models.NewEntityMother
import com.orange.newly.data.models.NewsPaginationStateEntity
import com.orange.newly.data.models.PopularNewDtoMother
import com.orange.newly.data.models.SearchNewDtoMother
import com.orange.newly.data.models.TopNewDtoMother
import com.orange.newly.data.paging.CategoryNewsRemoteMediator
import com.orange.newly.data.paging.SearchNewsPagingSource
import com.orange.newly.data.utils.TestPagingSource
import com.orange.newly.domain.models.Category
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.experimental.runners.Enclosed
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DisplayName("News Repository Test Suite")
@ExtendWith(MockKExtension::class)
@RunWith(Enclosed::class)
class NewsRepositoryImplTest {

    private lateinit var sut: NewsRepositoryImpl

    @MockK(relaxUnitFun = true) private lateinit var mockDataSource: NewsDataSource
    @MockK(relaxUnitFun = true) private lateinit var mockDataStore: NewsDataStore
    @MockK(relaxUnitFun = true) private lateinit var mockRemoteMediatorFactory: CategoryNewsRemoteMediator.Factory
    @MockK(relaxUnitFun = true) private lateinit var mockPagingSourceFactory: SearchNewsPagingSource.Factory

    @BeforeEach
    fun setup() {
        sut = NewsRepositoryImpl(
            dataSource = mockDataSource,
            dataStore = mockDataStore,
            categoryNewsRemoteMediatorFactory = mockRemoteMediatorFactory,
            searchNewsPagingSourceFactory = mockPagingSourceFactory
        )


        mockkStatic(Base64::class)
        every { Base64.encodeToString(any(), any()) } returns "base64"
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Nested
    @DisplayName("Get Top News Tests")
    inner class GetTopNewsTest {

        @Test
        fun `Given entities When fetching top news Then return domain models`() = runTest {
            val entities = NewEntityMother.list
            every { mockDataStore.getTopNews() } returns flowOf(entities)

            sut.getTopNews().test {
                val result = awaitItem()

                assertEquals(entities.size, result.size)
                assertEquals(entities.first().id, result.first().id)
                cancelAndIgnoreRemainingEvents()
            }
        }

    }

    @Nested
    @DisplayName("Get Popular News Tests")
    inner class GetPopularNewsTest  {

        @Test
        fun `Given entities When fetching popular news Then return domain models`() = runTest {
            val entities = NewEntityMother.list
            every { mockDataStore.getPopularNews() } returns flowOf(entities)

            sut.getPopularNews().test {
                val result = awaitItem()

                assertEquals(entities.size, result.size)
                assertEquals(entities.first().id, result.first().id)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Nested
    @DisplayName("Refresh Top News Tests")
    inner class RefreshTopNewsTest {

        @Test
        fun `Given api result When refreshing top news Then result is success`() = runTest {
            val dtoList = TopNewDtoMother.list
            coEvery { mockDataSource.getTopNews() } returns dtoList

            val result = sut.refreshTopNews()

            assertTrue(result is Success)
            coVerify { mockDataStore.addTopNews(any()) }
        }

    }

    @Nested
    @DisplayName("Refresh Top News Tests")
    inner class RefreshPopularNewsTest {

        @Test
        fun `Given api result When refreshing popular news Then result is success`() = runTest {
            val dtoList = PopularNewDtoMother.list
            coEvery { mockDataSource.getPopularNews() } returns dtoList

            val result = sut.refreshPopularNews()

            assertTrue(result is Success)
            coVerify { mockDataStore.addPopularNews(any()) }
        }

    }

    @Nested
    @DisplayName("Get New By ID Test")
    inner class GetNewByIdTest {

        @Test
        fun `Given new ID When fetching new Then return new`() = runTest {
            val testId = "new-1"
            val entity = NewEntityMother(id = testId, title = testId).build()
            coEvery { mockDataStore.getNew(testId) } returns entity

            val result = sut.getNewById(testId)

            assertTrue(result is Success)
            assertEquals(testId, result.value.id)
        }

        @Test
        fun `Given wrong ID When fetching ned Then return failure`() = runTest {
            coEvery { mockDataStore.getNew(any()) } returns null

            val result = sut.getNewById("")

            assertTrue(result is Failure)
        }

    }

    @Nested
    @DisplayName("Search News Test")
    inner class SearchNewsTest {

        @Test
        fun `Given query When fetching paged news Then return searched news`() = runTest {
            val query = "Spaguetti"
            val testDtos = SearchNewDtoMother.list
            val mockPagingSource = SearchNewsPagingSource(mockDataSource, query)

            coEvery { mockDataSource.searchNews(query, any()) } returns testDtos
            every { mockPagingSourceFactory.create(query) } returns mockPagingSource

            val data = sut.searchNews(query)

            val items = data.asSnapshot {
                scrollTo(2)
            }

            assertEquals(testDtos.size, items.size)
            assertEquals(testDtos.first().webUrl, items.first().url)
        }

    }

    @Nested
    @DisplayName("Category News Test")
    inner class GetNewsByCategoryTest {

        @Test
        fun `Given category When fetching new Then return new`() = runTest {
            val category = Category.TECH
            val testDto = SearchNewDtoMother.list
            val testEntities = testDto.toEntity()
            val testPagingSource = TestPagingSource(testEntities)

            val mockMediator = CategoryNewsRemoteMediator(mockDataSource, mockDataStore, category)

            coEvery { mockDataStore.getCategoryNewsPaginationState(category) } returns NewsPaginationStateEntity(category, 1)
            coEvery { mockDataSource.getNewsByCategory(category, any()) } returns testDto
            coEvery { mockDataStore.getCategoryNews(category) } returns testPagingSource
            every { mockRemoteMediatorFactory.create(category) } returns mockMediator

            val data = sut.getNewsByCategory(category)

            val items = data.asSnapshot {
                scrollTo(2)
            }

            assertEquals(testEntities.size, items.size)
            assertEquals(testEntities.first().url, items.first().url)
        }

    }

}