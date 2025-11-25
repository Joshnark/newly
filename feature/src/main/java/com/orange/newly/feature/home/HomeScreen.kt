package com.orange.newly.feature.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.orange.newly.feature.bookmark.BookmarkPage
import com.orange.newly.feature.home.models.HomeNavOption
import com.orange.newly.feature.home.models.HomeNavItem
import com.orange.newly.feature.home.widgets.BottomNavBar
import com.orange.newly.feature.home.widgets.Topbar
import com.orange.newly.feature.news.NewsPage
import kotlinx.coroutines.launch

val navigationItems = listOf(
    HomeNavItem(
        title = "News",
        icon = Icons.Default.Home,
        route = HomeNavOption.TopNews
    ),
    HomeNavItem(
        title = "Bookmark",
        icon = Icons.Default.Bookmark,
        route = HomeNavOption.Bookmark
    )
)

const val HOME_INITIAL_PAGE = 0

@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        pageCount = {
            navigationItems.size
        },
        initialPage = HOME_INITIAL_PAGE
    )

    Scaffold(
        topBar = {
            Topbar()
        },
        bottomBar = {
            BottomNavBar(
                navigationItems = navigationItems,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                }
            )
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(paddingValues)
        ) { page ->
            when(navigationItems[page].route) {
                is HomeNavOption.TopNews -> NewsPage()
                is HomeNavOption.Bookmark -> BookmarkPage()
            }
        }
    }
}