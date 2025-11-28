package com.orange.newly.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.orange.newly.feature.shared.NavigationRoute
import com.orange.newly.feature.home.HomeRoute
import com.orange.newly.feature.shared.extensions.paddingLarge
import com.orange.newly.feature.shared.theme.NewlyTheme
import com.orange.newly.feature.shared.widgets.MediumSpacer

@Composable
fun OnboardingScreen(
    navigate: (NavigationRoute) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().paddingLarge()
        ) {
            Text(
                text = "Newly",
                style = MaterialTheme.typography.displaySmall
            )

            MediumSpacer()

            Text(
                text = "Freshly-baked news around from the world for you",
                style = MaterialTheme.typography.titleLarge
            )

            MediumSpacer()

            Button(
                content = { Text("Get Started") },
                onClick = {
                    navigate(HomeRoute)
                }
            )
        }
    }
}

@Composable
@Preview
private fun PreviewOnBoarding() {
    NewlyTheme {
        OnboardingScreen {  }
    }
}