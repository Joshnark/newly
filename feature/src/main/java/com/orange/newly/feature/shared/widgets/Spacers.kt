package com.orange.newly.feature.shared.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.newly.feature.shared.Sizes

@Composable
fun TinySpacer() {
    Spacer(modifier = Modifier.size(Sizes.TINY))
}

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.size(Sizes.SMALL))
}

@Composable
fun MediumSpacer() {
    Spacer(modifier = Modifier.size(Sizes.MEDIUM))
}