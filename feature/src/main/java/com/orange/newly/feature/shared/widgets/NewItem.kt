package com.orange.newly.feature.shared.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.orange.newly.domain.models.New

@Composable
fun NewItem(new: New) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = "https://img.freepik.com/free-photo/closeup-scarlet-macaw-from-side-view-scarlet-macaw-closeup-head_488145-3540.jpg?semt=ais_hybrid&w=740&q=80",
            contentDescription = "Translated description of what the image contains",
            contentScale = ContentScale.Crop,
            modifier = Modifier.weight(0.3f).aspectRatio(1f)
        )

        Column(
            modifier = Modifier.weight(0.7f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(new.source.orEmpty())

            Text(
                new.title,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                new.author,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                new.publishedAt,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}