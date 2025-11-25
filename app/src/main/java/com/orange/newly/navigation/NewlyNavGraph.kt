package com.orange.newly.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.orange.newly.feature.home.homePage
import com.orange.newly.feature.onboarding.OnboardingRoute
import com.orange.newly.feature.onboarding.onboardingPage

@Composable
fun NewlyNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnboardingRoute
    ) {
        onboardingPage(navController::navigate)
        homePage()
    }
}