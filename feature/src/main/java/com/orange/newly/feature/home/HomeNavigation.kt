package com.orange.newly.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.newly.feature.shared.NavigationRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute: NavigationRoute

fun NavGraphBuilder.homePage() {
    composable<HomeRoute> {
        HomeScreen()
    }
}