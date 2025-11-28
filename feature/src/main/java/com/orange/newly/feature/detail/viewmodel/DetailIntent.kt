package com.orange.newly.feature.detail.viewmodel

sealed interface DetailIntent {
    data class LoadNew(val id: String): DetailIntent
}