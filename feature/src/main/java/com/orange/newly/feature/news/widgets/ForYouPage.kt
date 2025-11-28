package com.orange.newly.feature.news.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.orange.newly.domain.models.New
import com.orange.newly.feature.foryou.ForYouScreen
import com.orange.newly.feature.foryou.viewmodel.ForYouIntent
import com.orange.newly.feature.foryou.viewmodel.ForYouViewModel

@Composable
fun ForYouPage(onOpenDetail: (New) -> Unit) {
    val viewModel = hiltViewModel<ForYouViewModel>()
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(viewModel) {
        viewModel.setIntent(ForYouIntent.LoadForYouNews)
        viewModel.setIntent(ForYouIntent.RefreshAll)
    }

    ForYouScreen(
        state = state,
        onEvent = viewModel::setIntent,
        onOpenDetail = onOpenDetail
    )
}