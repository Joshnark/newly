package com.orange.newly.feature.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.orange.newly.domain.models.New
import com.orange.newly.feature.detail.viewmodel.DetailUiState
import com.orange.newly.feature.detail.widget.DetailBody
import com.orange.newly.feature.detail.widget.DetailError
import com.orange.newly.feature.shared.Sizes
import com.orange.newly.feature.shared.extensions.paddingSmall
import com.orange.newly.feature.shared.theme.NewlyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailUiState,
    goBack: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(
                        content = { Icon(Icons.Default.Share, contentDescription = null) },
                        onClick = {  }
                    )
                },
                title = {},
                navigationIcon = {
                    IconButton(
                        content = { Icon(Icons.Default.Close, contentDescription = null) },
                        onClick = { goBack.invoke() }
                    )
                }
            )
        },
        bottomBar = {
            if (state is DetailUiState.NewLoadSuccess) {
                Button(
                    modifier = Modifier.fillMaxWidth().paddingSmall(),
                    shape = RoundedCornerShape(Sizes.SMALL),
                    content = {
                        Text("Read complete new")
                    },
                    onClick = {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(state.new.url))
                        context.startActivity(browserIntent)
                    }
                )
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            when(state) {
                is DetailUiState.NewLoadSuccess -> {
                    DetailBody(state.new)
                }
                is DetailUiState.Error -> {
                    DetailError()
                }
                is DetailUiState.Idle -> Unit
            }
        }
    }
}

@Preview(device = Devices.PIXEL, showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun DetailScreenPreview() {
    NewlyTheme {
        DetailScreen(
            state = DetailUiState.NewLoadSuccess(
                New(
                    id ="12",
                    title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    author = "John Doe | Josh Doe | Juan Doe",
                    content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    publishedAt = "12/12/1212",
                    source = "source",
                    url = "asd",
                    urlToImage = "1243asdgfaf"
                )
            )
        ) { }
    }
}