package com.orange.newly.feature.category

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.orange.newly.domain.models.New
import com.orange.newly.feature.shared.widgets.NewItem

@Composable
fun CategoryNewsScreen(
    pagingItems: LazyPagingItems<New>
) {
    LazyColumn {
        items(pagingItems.itemCount) {
            pagingItems[it]?.let { new ->
                NewItem(new)
            }
        }
    }
}