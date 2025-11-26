package com.orange.newly.feature.foryou

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.orange.newly.domain.models.New
import com.orange.newly.feature.shared.NewlyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun ForYouScreen(
    recommendedPagingData: LazyPagingItems<New>,
) {
    LazyColumn {
        item {
            val state = rememberPagerState(initialPage = 0, pageCount = { 2 })

            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Near you", modifier = Modifier.padding(horizontal = 8.dp))

                HorizontalPager(
                    state = state,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Card(
                        colors = CardDefaults.cardColors(Color.Transparent),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        AsyncImage(
                            model = "https://img.freepik.com/free-photo/closeup-scarlet-macaw-from-side-view-scarlet-macaw-closeup-head_488145-3540.jpg?semt=ais_hybrid&w=740&q=80",
                            contentDescription = "Translated description of what the image contains",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth().aspectRatio(1.4f)
                        )
                    }
                }
            }
        }

        item {
            Text("Recommended", modifier = Modifier.padding(horizontal = 8.dp))
        }

        items(recommendedPagingData.itemCount) {
            recommendedPagingData[it]?.let { item ->
                NewItem(item)
            }
        }

    }
}

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
            Text(new.source)

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

@Preview(device = Devices.PIXEL, backgroundColor = 0xffffffff, showBackground = true)
@Composable
private fun ForYouScreenPreview() {
    NewlyTheme {
        ForYouScreen(
            recommendedPagingData = flowOf(PagingData.from(listOf(
                New(
                    title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    author = "John Doe | Josh Doe | Juan Doe",
                    content = "lorem ipsum",
                    description = "lorem ipsum",
                    publishedAt = "12/12/1212",
                    source = "source",
                    url = "asd",
                    urlToImage = "1243asdgfaf"
                ),
                New(
                    title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    author = "John Doe | Josh Doe | Juan Doe",
                    content = "lorem ipsum",
                    description = "lorem ipsum",
                    publishedAt = "12/12/1212",
                    source = "source",
                    url = "asd",
                    urlToImage = "1243asdgfaf"
                )
            ))).collectAsLazyPagingItems()
        )
    }
}