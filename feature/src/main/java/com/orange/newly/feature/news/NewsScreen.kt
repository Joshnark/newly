package com.orange.newly.feature.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orange.newly.domain.models.Category
import com.orange.newly.feature.news.widgets.ForYouPage
import com.orange.newly.feature.news.widgets.CategoryNewsPage
import com.orange.newly.feature.shared.theme.NewlyTheme
import kotlinx.coroutines.launch

@Composable
fun NewsScreen() {
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
            selectedNavigationIndex = selectedNavigationIndex.value,
            onClick = {
                selectedNavigationIndex.value = categories.indexOf(it)
                scope.launch {
                    pagerState.animateScrollToPage(categories.indexOf(it))
                }
            }
        )

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            when(categories[page]) {
                Category.HOME -> ForYouPage()
                else -> CategoryNewsPage()
            }
        }
    }
}

@Composable
fun Categories(categories: List<Category>, selectedNavigationIndex: Int, onClick: (Category) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(categories) {
            CategoryItem(
                category = it,
                isSelected = categories.indexOf(it) == selectedNavigationIndex,
                onClick = onClick
            )
        }
    }
}

@Composable
fun CategoryItem(category: Category, isSelected: Boolean, onClick: (Category) -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(5.dp)
            ).padding(
                4.dp
            ).clickable(
                onClick = {
                    onClick.invoke(category)
                }
            )
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category.title,
            style = TextStyle(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

val Category.title
    get() = when(this) {
        Category.HOME -> "For you"
        else -> this.value
    }

@Preview
@Composable
private fun NewsPreview() {
    NewlyTheme {
        NewsScreen()
    }
}