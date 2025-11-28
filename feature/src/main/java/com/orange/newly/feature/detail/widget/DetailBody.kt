package com.orange.newly.feature.detail.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.orange.newly.domain.models.New
import com.orange.newly.feature.shared.extensions.paddingMedium
import com.orange.newly.feature.shared.widgets.MediumSpacer
import com.orange.newly.feature.shared.widgets.SmallSpacer
import com.orange.newly.ui.R

@Composable
fun DetailBody(new: New) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).paddingMedium()
    ) {
        Text(
            text = new.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        SmallSpacer()

        AsyncImage(
            modifier = Modifier.fillMaxWidth().aspectRatio(1.4f),
            model = new.urlToImage,
            error = painterResource(R.drawable.nope),
            placeholder = painterResource(R.drawable.loadingcat),
            contentDescription = null
        )

        MediumSpacer()

        if (new.author.isNotBlank()) {
            Text(
                text = new.author,
                style = MaterialTheme.typography.titleMedium,
            )

            MediumSpacer()
        }

        Text(
            text = new.description
        )
    }
}

@Composable
@Preview(device = Devices.PIXEL, showBackground = true, backgroundColor = 0xffffffff)
private fun DetailBodyPreview() {
    DetailBody(
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
}