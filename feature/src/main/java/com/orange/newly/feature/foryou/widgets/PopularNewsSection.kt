package com.orange.newly.feature.foryou.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.orange.newly.domain.models.New
import com.orange.newly.feature.foryou.viewmodel.ForYouUIState
import com.orange.newly.feature.shared.Sizes
import com.orange.newly.feature.shared.extensions.paddingMedium
import com.orange.newly.feature.shared.theme.NewlyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val MAX_POP_NEWS = 7

@Composable
fun PopularNewsSection(state: ForYouUIState, onOpenDetail: (New) -> Unit) {
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth()) {
        PopularViewsTitle()

        when {
            state.errorLoadingPopularNews != null -> NewsError()
            state.popularNews.isNotEmpty() -> {
                val news = state.popularNews.slice(0 ..state.popularNews.size.coerceAtMost(MAX_POP_NEWS)) // we only want 7, those large images are expensive
                val pagerState = rememberPagerState(initialPage = 0, pageCount = { MAX_POP_NEWS })

                HorizontalPager(
                    state = pagerState,
                    verticalAlignment = Alignment.CenterVertically,
                ) { page ->
                    PopularNewItem(news[page], onOpenDetail)
                }

                LaunchedEffect(pagerState.currentPage) {
                    scope.launch {
                        delay(5000)
                        with(pagerState) {
                            val target = if (currentPage < pageCount - 1) currentPage + 1 else 0
                            animateScrollToPage(target)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PopularNewItem(new: New, onOpenDetail: (New) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        shape = RoundedCornerShape(Sizes.MEDIUM),
        elevation = CardDefaults.cardElevation(Sizes.ONE),
        modifier = Modifier.fillMaxWidth().paddingMedium().clickable { onOpenDetail.invoke(new) }
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            AsyncImage(
                model = new.urlToImage,
                contentDescription = "Translated description of what the image contains",
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.Medium,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.4f)
            )

            Text(
                text = new.title,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
                modifier = Modifier
                    .paddingMedium()
                    .background(color = Color.Black, shape = RoundedCornerShape(Sizes.MEDIUM))
                    .paddingMedium()
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xffffffff, device = Devices.PIXEL)
private fun PopularItemPreview() {
    NewlyTheme {
        PopularNewItem(
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
        ) { }
    }
}