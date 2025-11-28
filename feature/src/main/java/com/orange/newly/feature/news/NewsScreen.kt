package com.orange.newly.feature.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import com.orange.newly.feature.news.widgets.Categories
import com.orange.newly.feature.news.widgets.NewsContainer
import com.orange.newly.feature.shared.theme.NewlyTheme
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(onOpenDetail: (New) -> Unit) {
    val scope = rememberCoroutineScope()
    val categories = Category.entries
    val initialIndex = categories.indexOf(Category.HOME)

    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(initialIndex)
    }

    val pagerState = rememberPagerState(
        pageCount = {
            categories.size
        },
        initialPage = initialIndex
    )

    Column{
        Categories(
            categories = categories,
            selectedNavigationIndex = selectedNavigationIndex.intValue,
            onClick = {
                selectedNavigationIndex.intValue = categories.indexOf(it)
                scope.launch {
                    pagerState.animateScrollToPage(categories.indexOf(it))
                }
            }
        )

        NewsContainer(
            state = pagerState,
            categories = categories,
            index = selectedNavigationIndex.intValue,
            onOpenDetail = onOpenDetail
        )
    }
}

@Preview
@Composable
private fun NewsPreview() {
    NewlyTheme {
        NewsScreen {}
    }
}