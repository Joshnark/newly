package com.orange.newly.feature.detail

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.orange.newly.feature.detail.viewmodel.DetailIntent
import com.orange.newly.feature.detail.viewmodel.DetailViewModel
import com.orange.newly.feature.shared.NavigationRoute
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(val newId: String): NavigationRoute

fun NavGraphBuilder.detailPage(popBackStack: () -> Unit) {
    composable<DetailRoute> { backstackEntry ->
        val viewModel = hiltViewModel<DetailViewModel>()
        val id = backstackEntry.toRoute<DetailRoute>().newId
        val state = viewModel.state.collectAsState().value

        LaunchedEffect(viewModel) {
            viewModel.setEvent(DetailIntent.LoadNew(id))
        }

        DetailScreen(
            state = state,
            goBack = popBackStack
        )
    }
}