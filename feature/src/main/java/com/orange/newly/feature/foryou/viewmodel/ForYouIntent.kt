package com.orange.newly.feature.foryou.viewmodel

sealed interface ForYouIntent {
    data object LoadForYouNews: ForYouIntent
    data object RefreshAll: ForYouIntent
}