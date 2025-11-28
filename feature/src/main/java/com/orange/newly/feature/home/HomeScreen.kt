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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.orange.newly.feature.bookmark.BookmarkPage
import com.orange.newly.feature.detail.DetailRoute
import com.orange.newly.feature.home.models.HomeNavOption
import com.orange.newly.feature.home.models.HomeNavItem
import com.orange.newly.feature.home.widgets.BottomNavBar
import com.orange.newly.feature.home.widgets.Topbar
import com.orange.newly.feature.news.NewsPage
import com.orange.newly.feature.search.SearchRoute
import com.orange.newly.feature.shared.NavigationRoute
import com.orange.newly.feature.shared.theme.NewlyTheme
import kotlinx.coroutines.launch

val navigationItems = listOf(
    HomeNavItem(
        title = "News",
        icon = Icons.Default.Home,
        route = HomeNavOption.News
    ),
    HomeNavItem(
        title = "Bookmark",
        icon = Icons.Default.Bookmark,
        route = HomeNavOption.Bookmark
    )
)

const val HOME_INITIAL_PAGE = 0

@Composable
fun HomeScreen(navigate: (NavigationRoute) -> Unit) {
    val pagerState = rememberPagerState(
        pageCount = {
            navigationItems.size
        },
        initialPage = HOME_INITIAL_PAGE
    )

    Scaffold(
        topBar = {
            Topbar(
                onSearch = { navigate.invoke(SearchRoute) },
                onMenu = {}
            )
        },
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(paddingValues),
            userScrollEnabled = false
        ) { page ->
            when(navigationItems[page].route) {
                is HomeNavOption.News -> NewsPage(
                    onOpenDetail = { new ->
                        navigate.invoke(DetailRoute(new.id))
                    }
                )
                is HomeNavOption.Bookmark -> BookmarkPage()
            }
        }
    }
}