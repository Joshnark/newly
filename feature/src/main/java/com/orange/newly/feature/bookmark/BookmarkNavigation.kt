package com.orange.newly.feature.bookmark

import androidx.compose.runtime.Composable
import com.orange.newly.feature.shared.NavigationRoute

data object BookmarkRoute: NavigationRoute

@Composable
fun BookmarkPage() {
    BookmarkScreen()
}