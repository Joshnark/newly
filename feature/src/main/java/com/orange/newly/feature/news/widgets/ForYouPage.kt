package com.orange.newly.feature.news.widgets

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.orange.newly.feature.foryou.ForYouScreen
import com.orange.newly.feature.foryou.viewmodel.ForYouViewModel

@Composable
fun ForYouPage() {
    val viewModel = hiltViewModel<ForYouViewModel>()
    val recommendedPagingData = viewModel.recommendedNewsPagingData.collectAsLazyPagingItems()

    ForYouScreen(
        recommendedPagingData
    )
}