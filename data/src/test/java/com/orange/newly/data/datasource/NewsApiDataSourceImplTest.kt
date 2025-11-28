package com.orange.newly.data.datasource

import android.net.Network
import com.orange.newly.data.api.NewsApi
import com.orange.newly.data.datasources.NewsApiDataSourceImpl
import com.orange.newly.data.models.PopularNewDtoMother
import com.orange.newly.data.models.PopularNewsResponse
import com.orange.newly.data.models.SearchNewDtoMother
import com.orange.newly.data.models.SearchNewsResponse
import com.orange.newly.data.models.SearchResponseData
import com.orange.newly.data.models.TopNewDtoMother
import com.orange.newly.data.models.TopNewsResponse
import com.orange.newly.domain.exceptions.NetworkException
import com.orange.newly.domain.exceptions.TooManyRequestsException
import com.orange.newly.domain.models.Category
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@DisplayName("News Api Data Source Test Suite")
@ExtendWith(MockKExtension::class)
class NewsApiDataSourceImplTest {

    private lateinit var sut: NewsApiDataSourceImpl

    @MockK(relaxUnitFun = true) private lateinit var mockApi: NewsApi

    @BeforeEach
    fun setup() {
        sut = NewsApiDataSourceImpl(mockApi)
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Nested
    @DisplayName("Get Popular News Test")
    inner class GetPopularNewsTest {

        @Test
        fun `Given api success When getting popular news Then return dto`() = runTest {
            val dtos = PopularNewDtoMother.list
            val response = PopularNewsResponse("OK", dtos.size, dtos)
            coEvery { mockApi.getPopularNews() } returns response

            val result = sut.getPopularNews()

            assertEquals(dtos.size, result.size)
            assertEquals(dtos.first().url, result.first().url)
        }

        @Test
        fun `Given that nytimes api is too restrictive When getting popular news a bunch of times Then throw 429 error`() = runTest {
            val exception = HttpException(
                Response.error<String>(429, "".toResponseBody())
            )

            coEvery { mockApi.getPopularNews() } throws exception

            assertFailsWith<TooManyRequestsException> {
                sut.getPopularNews()
            }
        }

        @Test
        fun `Given any network error When getting popular news a bunch of times Then throw error`() = runTest {
            val exception = IOException("Your router broke")
            coEvery { mockApi.getPopularNews() } throws exception

            assertFailsWith<NetworkException> {
                sut.getPopularNews()
            }
        }

    }

    @Nested
    @DisplayName("Get Top News Test")
    inner class GetTopNewsTest {

        @Test
        fun `Given api success When getting top news Then return dto`() = runTest {
            val dtos = TopNewDtoMother.list
            val response = TopNewsResponse("OK", "", dtos.size, dtos)
            coEvery { mockApi.getTopNews() } returns response

            val result = sut.getTopNews()

            assertEquals(dtos.size, result.size)
            assertEquals(dtos.first().url, result.first().url)
        }

    }

    @Nested
    @DisplayName("Get News By Cat Test")
    inner class GetNewsByCategoryTest {
        @Test
        fun `Given api success When getting new by cat news Then return dto`() = runTest {
            val category = Category.TECH
            val dtos = SearchNewDtoMother.list
            val response = SearchNewsResponse("OK", SearchResponseData(dtos))
            coEvery { mockApi.getNewsByCategory(any(), any()) } returns response

            val result = sut.getNewsByCategory(category, 1)

            assertEquals(dtos.size, result.size)
            assertEquals(dtos.first().webUrl, result.first().webUrl)
        }
    }

    @Nested
    @DisplayName("Seach News Test")
    inner class SearchNewsTest {
        @Test
        fun `Given api success When searching news Then return dto`() = runTest {
            val query = "Pasta"
            val dtos = SearchNewDtoMother.list
            val response = SearchNewsResponse("OK", SearchResponseData(dtos))
            coEvery { mockApi.searchNews(query, any()) } returns response

            val result = sut.searchNews(query, 1)

            assertEquals(dtos.size, result.size)
            assertEquals(dtos.first().webUrl, result.first().webUrl)
        }
    }

}