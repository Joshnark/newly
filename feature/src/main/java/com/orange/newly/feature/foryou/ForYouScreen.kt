package com.orange.newly.feature.foryou

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.orange.newly.domain.models.New
import com.orange.newly.feature.foryou.viewmodel.ForYouIntent
import com.orange.newly.feature.foryou.viewmodel.ForYouUIState
import com.orange.newly.feature.foryou.widgets.PopularNewsSection
import com.orange.newly.feature.foryou.widgets.TopStoriesTitle
import com.orange.newly.feature.shared.theme.NewlyTheme
import com.orange.newly.feature.shared.widgets.NewItem

@Composable
fun ForYouScreen(
    state: ForYouUIState,
    onEvent: (ForYouIntent) -> Unit
) {
    LazyColumn {
        item {
            PopularNewsSection(state)
        }

        item {
            TopStoriesTitle()
        }

        items(state.topNews) { item ->
            NewItem(item)
        }
    }
}
@Preview(device = Devices.PIXEL, backgroundColor = 0xffffffff, showBackground = true)
@Composable
private fun ForYouScreenPreview() {
    val mockList = listOf(
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
    )

    NewlyTheme {
        ForYouScreen(
            state = ForYouUIState(
                popularNews = mockList,
                topNews = mockList
            ),
            onEvent = {}
        )
    }
}