package com.orange.newly.feature.news.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.orange.newly.domain.models.Category
import com.orange.newly.feature.category.CategoryNewsScreen
import com.orange.newly.feature.category.viewmodel.CategoryIntent
import com.orange.newly.feature.category.viewmodel.CategoryViewmodel

@Composable
fun CategoryNewsPage(category: Category) {
    val viewModel = hiltViewModel<CategoryViewmodel>()
    val pagingItems = viewModel.newsPagingData.collectAsLazyPagingItems()

    LaunchedEffect(viewModel) {
        viewModel.setIntent(CategoryIntent.LoadCategory(category))
    }

    CategoryNewsScreen(pagingItems)
}