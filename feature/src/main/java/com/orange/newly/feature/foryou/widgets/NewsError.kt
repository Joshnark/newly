package com.orange.newly.feature.foryou.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.orange.newly.feature.shared.extensions.paddingLarge

@Composable
fun NewsError() {
    Column(modifier = Modifier.fillMaxWidth().paddingLarge()) {
        Text(
            text = "An error has happened loading popular views! :(",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )
    }
}