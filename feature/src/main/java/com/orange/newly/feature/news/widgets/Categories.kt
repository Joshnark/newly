package com.orange.newly.feature.news.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.orange.newly.domain.models.Category
import com.orange.newly.feature.shared.Sizes


@Composable
fun Categories(categories: List<Category>, selectedNavigationIndex: Int, onClick: (Category) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Sizes.SMALL),
        contentPadding = PaddingValues(Sizes.MEDIUM)
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