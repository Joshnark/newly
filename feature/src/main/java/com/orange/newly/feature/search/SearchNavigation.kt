package com.orange.newly.feature.search

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.orange.newly.feature.search.viewmodel.SearchViewModel
import com.orange.newly.feature.shared.NavigationRoute
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute: NavigationRoute

fun NavGraphBuilder.searchPage() {
    composable<SearchRoute> {
        val viewmodel = hiltViewModel<SearchViewModel>()
        val pagingItems = viewmodel.newsPagingData.collectAsLazyPagingItems()

        SearchScreen(
            pagingItems = pagingItems,
            onEvent = { viewmodel.setIntent(it) }
        )
    }
}