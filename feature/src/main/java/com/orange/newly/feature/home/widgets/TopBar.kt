package com.orange.newly.feature.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
            IconButton(
                content = {
                    Icon(Icons.Filled.Menu, contentDescription = null)
                },
                onClick = {
                    onMenu.invoke()
                }
            )
        },
        actions = {
            IconButton(
                content = {
                    Icon(Icons.Filled.Search, contentDescription = null)
                },
                onClick = {
                    onSearch.invoke()
                }
            )
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