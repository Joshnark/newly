package com.orange.newly.feature.foryou.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.newly.feature.shared.extensions.paddingMedium

@Composable
fun PopularViewsTitle() {
    Text("Popular Views", style = MaterialTheme.typography.titleLarge, modifier = Modifier.paddingMedium())
}

@Composable
fun TopStoriesTitle() {
    Text("Top Stories", style = MaterialTheme.typography.titleLarge, modifier = Modifier.paddingMedium())
}
