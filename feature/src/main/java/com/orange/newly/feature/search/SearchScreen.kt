package com.orange.newly.feature.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.orange.newly.domain.models.New
import com.orange.newly.feature.search.viewmodel.SearchIntent
import com.orange.newly.feature.search.widgets.SearchTopBar
import com.orange.newly.feature.shared.widgets.NewItem

@Composable
fun SearchScreen(
    pagingItems: LazyPagingItems<New>,
    onEvent: (SearchIntent) -> Unit
) {
    Scaffold(
        topBar = {
            SearchTopBar { value -> onEvent.invoke(SearchIntent.Search(value)) }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            items(pagingItems.itemCount) {
                pagingItems[it]?.let { new ->
                    NewItem(new)
                }
            }
        }
    }
}