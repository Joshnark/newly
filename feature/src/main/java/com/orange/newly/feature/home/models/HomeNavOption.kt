package com.orange.newly.feature.home.models

import com.orange.newly.feature.shared.NavigationRoute
import com.orange.newly.feature.bookmark.BookmarkRoute
import com.orange.newly.feature.news.NewsRoute

sealed class HomeNavOption(val route: NavigationRoute) {
    data object News: HomeNavOption(NewsRoute)
    data object Bookmark: HomeNavOption(BookmarkRoute)
}