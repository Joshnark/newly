package com.orange.newly.feature.search.viewmodel

sealed interface SearchIntent {
    data class Search(val query: String): SearchIntent
}