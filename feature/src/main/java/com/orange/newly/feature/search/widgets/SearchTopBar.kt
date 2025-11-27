package com.orange.newly.feature.search.widgets

import android.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.orange.newly.feature.shared.theme.NewlyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(onSearch: (String) -> Unit) {
    var value by remember { mutableStateOf("") }

    TopAppBar(
        title = {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = { newValue -> value = newValue },
                maxLines = 1,
                trailingIcon = {
                    IconButton(
                        onClick = { onSearch.invoke(value) },
                        content = {
                            Icon(Icons.Default.Search, contentDescription = null)
                        }
                    )
                }
            )
        }
    )
}

@Preview
@Composable
fun SearchTopBarPreview() {
    NewlyTheme {
        SearchTopBar {  }
    }
}