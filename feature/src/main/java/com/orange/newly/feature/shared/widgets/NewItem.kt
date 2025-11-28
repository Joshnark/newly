package com.orange.newly.feature.shared.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.orange.newly.domain.models.New
import com.orange.newly.feature.shared.Sizes
import com.orange.newly.ui.R

@Composable
fun NewItem(new: New, onClick: (New) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = Sizes.MEDIUM).clickable { onClick.invoke(new) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(0.3f),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = new.urlToImage,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp).aspectRatio(1f).clip(
                    RoundedCornerShape(Sizes.MEDIUM)
                ),
                placeholder = painterResource(R.drawable.loadingcat),
                error = painterResource(R.drawable.nope),
                filterQuality = FilterQuality.Low
            )
        }


        Column(
            modifier = Modifier.weight(0.7f),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = new.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            SmallSpacer()

            Text(
                text = new.author,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (new.publishedAt.isNotBlank()) {
                SmallSpacer()

                Text(
                    text = new.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(device = Devices.PIXEL, backgroundColor = 0xffffffff, showBackground = true)
@Composable
private fun NewItemPreview() {
    NewItem(
        New(
            id = "12",
            title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            author = "John Doe | Josh Doe | Juan Doe",
            content = "lorem ipsum",
            description = "lorem ipsum",
            publishedAt = "12/12/1212",
            source = "source",
            url = "asd",
            urlToImage = "1243asdgfaf"
        )
    ) {}
}