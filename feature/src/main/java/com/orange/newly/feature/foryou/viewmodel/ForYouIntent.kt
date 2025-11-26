package com.orange.newly.feature.foryou.viewmodel

sealed interface ForYouIntent {
    data object LoadLocalizedNews: ForYouIntent
}