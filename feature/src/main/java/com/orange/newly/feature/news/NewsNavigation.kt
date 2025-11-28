package com.orange.newly.feature.news

import androidx.compose.runtime.Composable
import com.orange.newly.domain.models.New
import com.orange.newly.feature.shared.NavigationRoute
import kotlinx.serialization.Serializable

@Serializable
data object NewsRoute: NavigationRoute

@Composable
fun NewsPage(onOpenDetail: (New) -> Unit) {
    NewsScreen(onOpenDetail)
}