package com.orange.newly.feature.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.orange.newly.feature.shared.NavigationRoute
import com.orange.newly.feature.home.HomeRoute

@Composable
fun OnboardingScreen(
    navigate: (NavigationRoute) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Newly"
            )

            Text(
                text = "Freshly-baked news around from the world for you"
            )

            Button(
                content = { Text("Get Started") },
                onClick = {
                    navigate(HomeRoute)
                }
            )
        }
    }
}