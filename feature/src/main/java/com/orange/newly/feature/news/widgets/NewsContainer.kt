package com.orange.newly.feature.news.widgets

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import com.orange.newly.feature.home.viewmodel.HomeIntent
import com.orange.newly.feature.home.viewmodel.HomeViewmodel
import com.orange.newly.feature.shared.widgets.NewsList

@Composable
fun NewsContainer(
    state: PagerState,
    categories: List<Category>,
    index: Int,
    onOpenDetail: (New) -> Unit
) {
    val viewModel = hiltViewModel<HomeViewmodel>()

    LaunchedEffect(state.currentPage) {
        viewModel.setIntent(HomeIntent.LoadCategory(categories[index]))
    }

    val pagingItems = viewModel.newsPagingData.collectAsLazyPagingItems()

    HorizontalPager(
        state = state,
        userScrollEnabled = false,
        beyondViewportPageCount = 0,
        key = { categories[it].name }
    ) { page ->
        when(categories[page]) {
            Category.HOME -> ForYouPage(onOpenDetail)
            else -> {
                NewsList(
                    pagingItems = pagingItems,
                    onOpenDetail = onOpenDetail
                )
            }
        }
    }
}