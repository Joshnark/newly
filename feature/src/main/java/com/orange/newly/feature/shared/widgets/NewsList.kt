package com.orange.newly.feature.shared.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.orange.newly.domain.exceptions.TooManyRequestsException
import com.orange.newly.domain.models.New
import com.orange.newly.feature.shared.extensions.paddingSmall

@Composable
fun NewsList(pagingItems: LazyPagingItems<New>, modifier: Modifier = Modifier, paddingValues: PaddingValues = PaddingValues.Zero, onOpenDetail: (New) -> Unit) {
    LazyColumn(modifier = modifier.fillMaxSize().padding(paddingValues)) {
        val refreshState = pagingItems.loadState.refresh

        if (refreshState !is LoadState.Loading && refreshState !is LoadState.Error) {
            items(pagingItems.itemCount) {
                pagingItems[it]?.let { new ->
                    NewItem(new, onOpenDetail)
                }
            }
        }

        when(refreshState) {
            is LoadState.Loading -> if (pagingItems.itemCount == 0) loadingItem()
            is LoadState.Error -> errorItem(refreshState, onRetry = { pagingItems.retry() })
            is LoadState.NotLoading -> Unit
        }

        when(val state = pagingItems.loadState.append) {
            is LoadState.Loading -> loadingItem()
            is LoadState.Error -> errorItem(state, onRetry = { pagingItems.retry() })
            is LoadState.NotLoading -> Unit
        }
    }
}

fun LazyListScope.loadingItem() {
    item {
        Column(
            modifier = Modifier.fillMaxWidth().paddingSmall(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            SmallSpacer()
            Text("Loading", style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
            SmallSpacer()
            Text("Prepare for unforeseen consequences", style = MaterialTheme.typography.labelSmall)
        }
    }
}

fun LazyListScope.errorItem(state: LoadState.Error, onRetry: () -> Unit) {
    val text = if (state.error is TooManyRequestsException) {
        "NYT request limit reached\nWait one minute please or go drink some coffee"
    } else {
        "Something went wrong :/"
    }

    item {
        Column(
            modifier = Modifier.fillMaxWidth().paddingSmall(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Error, contentDescription = null)
            SmallSpacer()
            Text("Error", style = MaterialTheme.typography.titleLarge)
            SmallSpacer()
            Text(text, style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center)
            SmallSpacer()
            Button(
                content = { Text("Refresh") },
                onClick = { onRetry.invoke() }
            )
        }
    }
}