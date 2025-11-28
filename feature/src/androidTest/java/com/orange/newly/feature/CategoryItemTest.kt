package com.orange.newly.feature

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orange.newly.domain.models.Category
import com.orange.newly.feature.news.widgets.CategoryItem
import org.junit.Rule
import org.junit.Test

class CategoryItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenCategory_whenNoSelected_thenDisplays() {
        composeTestRule.setContent {
            CategoryItem(
                category = Category.TECH,
                isSelected = false,
                onClick = { }
            )
        }

        composeTestRule
            .onNodeWithText("Technology")
            .assertExists()
            .assertIsDisplayed()
    }



    @Test
    fun givenCategory_whenClicked_thenInvokesCallback() {
        var selectedCategory: Category? = null

        composeTestRule.setContent {
            CategoryItem(
                category = Category.TECH,
                isSelected = false,
                onClick = {
                    selectedCategory = it
                }
            )
        }

        composeTestRule
            .onNodeWithText("Technology")
            .performClick()

        assert(selectedCategory == Category.TECH)
    }

}