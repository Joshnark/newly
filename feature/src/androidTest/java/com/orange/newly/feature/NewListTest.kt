package com.orange.newly.feature

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.orange.newly.feature.models.NewMotherObj
import com.orange.newly.feature.shared.widgets.NewsList
import com.orange.newly.feature.utils.TestPagingSource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenPagedNews_whenLoadedInLazyColumn_displaysItems() {
        val mockData = NewMotherObj.list

        val pager = Pager(
            config = PagingConfig(10),
            pagingSourceFactory = { TestPagingSource(mockData) }
        )

        composeTestRule.setContent {
            NewsList(
                pagingItems = pager.flow.collectAsLazyPagingItems()
            ) {}
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(mockData.first().title).assertExists()
    }

}