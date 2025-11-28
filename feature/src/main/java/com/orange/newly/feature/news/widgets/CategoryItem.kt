package com.orange.newly.feature.news.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.orange.newly.domain.models.Category
import com.orange.newly.feature.shared.Sizes
import com.orange.newly.feature.shared.extensions.paddingMedium
import com.orange.newly.feature.shared.extensions.paddingSmall

@Composable
fun CategoryItem(category: Category, isSelected: Boolean, onClick: (Category) -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(Sizes.LARGE)
            )
            .padding(horizontal = Sizes.LARGE, vertical = Sizes.MEDIUM)
            .clickable(
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
        Category.BUSINESS -> "Business"
        Category.HEALTH -> "Health"
        Category.SCIENCE -> "Science"
        Category.SPORTS -> "Sports"
        Category.TECH -> "Technology"
    }