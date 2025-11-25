package com.orange.newly.feature.onboarding

import androidx.navigation.NavGraphBuilder
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable
import com.orange.newly.feature.shared.NavigationRoute

@Serializable
data object OnboardingRoute: NavigationRoute

fun NavGraphBuilder.onboardingPage(navigate: (NavigationRoute) -> Unit) {
    composable<OnboardingRoute> {
        OnboardingScreen(navigate)
    }
}