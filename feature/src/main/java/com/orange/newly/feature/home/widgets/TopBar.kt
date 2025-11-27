package com.orange.newly.feature.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.orange.newly.feature.shared.theme.NewlyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(
    onSearch: () -> Unit,
    onMenu: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Newly!",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            Icon(Icons.Default.Menu, contentDescription = null, modifier = Modifier.clickable { onMenu.invoke() })
        },
        actions = {
            Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.clickable{ onSearch.invoke() })
        }
    )
}

@Preview
@Composable
private fun TopbarPreview() {
    NewlyTheme {
        Topbar({}, {})
    }
}