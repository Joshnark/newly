package com.orange.newly.feature.foryou.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.newly.feature.shared.extensions.paddingMedium

@Composable
fun PopularViewsTitle() {
    Text("Popular Views", modifier = Modifier.paddingMedium())
}

@Composable
fun TopStoriesTitle() {
    Text("Top Stories", modifier = Modifier.paddingMedium())
}
