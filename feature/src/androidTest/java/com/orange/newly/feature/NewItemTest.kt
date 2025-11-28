package com.orange.newly.feature

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.orange.newly.feature.models.NewMotherObj
import com.orange.newly.feature.shared.widgets.NewItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenNewArticle_whenLoaded_displaysData() {
        val testNew = NewMotherObj.single

        composeTestRule.setContent {
            NewItem(new = testNew) {}
        }

        composeTestRule
            .onNodeWithText(testNew.title)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(testNew.author)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(testNew.publishedAt)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun givenNewArticle_whenNoPubDate_pubDateDoesNotDisplays() {
        val testNew = NewMotherObj.single.copy(publishedAt = "")

        composeTestRule.setContent {
            NewItem(new = testNew) {}
        }

        composeTestRule
            .onNodeWithText(testNew.publishedAt)
            .assertDoesNotExist()
    }

}