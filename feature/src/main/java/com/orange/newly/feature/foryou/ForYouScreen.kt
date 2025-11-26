package com.orange.newly.feature.foryou

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import com.orange.newly.domain.models.New
import com.orange.newly.feature.shared.NewlyTheme

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
            Text(new.title)

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(new.author)
                Text(new.publishedAt)
            }
        }
    }
}

@Preview
@Composable
private fun NewItemPreview() {
    NewItem(
        New(
            title = "titleasdada afawmflawkefma aaLlfmal asdf",
            author = "author",
            content = "aksdlmqwd",
            description = "askdlmnqwjknkf",
            publishedAt = "123/1/21/4",
            source = "Source",
            url = "asdqw",
            urlToImage = "asda"

        )
    )
}