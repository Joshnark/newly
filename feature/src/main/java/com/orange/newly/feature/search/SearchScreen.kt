package com.orange.newly.feature.search

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.compose.LazyPagingItems
import com.orange.newly.domain.models.New
import com.orange.newly.feature.search.viewmodel.SearchIntent
import com.orange.newly.feature.search.widgets.SearchTopBar
import com.orange.newly.feature.shared.widgets.NewsList

@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    pagingItems: LazyPagingItems<New>,
    onEvent: (SearchIntent) -> Unit
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            SearchTopBar { value -> onEvent.invoke(SearchIntent.Search(value)) }
        }
    ) { paddingValues ->
        NewsList(
            pagingItems =pagingItems,
            paddingValues = paddingValues,
            onOpenDetail = {}
        )
    }
}