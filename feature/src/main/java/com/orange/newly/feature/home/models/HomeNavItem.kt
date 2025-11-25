package com.orange.newly.feature.home.models

import androidx.compose.ui.graphics.vector.ImageVector

data class HomeNavItem(
    val title: String,
    val icon: ImageVector,
    val route: HomeNavOption
)