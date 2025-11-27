package com.orange.newly.feature.foryou.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.orange.newly.feature.foryou.viewmodel.ForYouUIState
import com.orange.newly.feature.shared.Sizes
import com.orange.newly.feature.shared.extensions.paddingMedium

@Composable
fun PopularNewsSection(state: ForYouUIState) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })

    Column(modifier = Modifier.fillMaxWidth()) {
        PopularViewsTitle()

        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                colors = CardDefaults.cardColors(Color.Transparent),
                shape = RoundedCornerShape(Sizes.MEDIUM),
                elevation = CardDefaults.cardElevation(Sizes.ONE),
                modifier = Modifier.fillMaxWidth().paddingMedium()
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