package com.orange.newly.feature.detail.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.orange.newly.feature.shared.extensions.paddingSmall
import com.orange.newly.feature.shared.widgets.SmallSpacer

@Composable
fun DetailError() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().paddingSmall(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Error, contentDescription = null)
            SmallSpacer()
            Text("Error", style = MaterialTheme.typography.titleLarge)
            SmallSpacer()
            Text("Something broke over here :/", style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center)
            SmallSpacer()
        }
    }
}