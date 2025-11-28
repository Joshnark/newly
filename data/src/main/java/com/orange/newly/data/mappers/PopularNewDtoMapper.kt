package com.orange.newly.data.mappers

import android.util.Base64
import androidx.paging.PagingData
import androidx.paging.map
import com.orange.newly.data.models.PopularNewDto
import com.orange.newly.data.models.NewEntity
import com.orange.newly.domain.models.New

fun PopularNewDto.toEntity() = NewEntity(
    id = Base64.encodeToString(url.toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP), // I will intentionally leave this here just to mockk static in tests
    author = byline,
    content = abstract,
    description = abstract,
    publishedAt = publishedDate,
    source = source,
    title = title,
    url = url,
    urlToImage = imageUrl,
)

fun List<PopularNewDto>.toEntity() = this.map { it.toEntity() }

fun PopularNewDto.toDomain() = New(
    id = Base64.encodeToString(url.toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP),
    author = byline,
    content = abstract,
    description = abstract,
    publishedAt = publishedDate,
    source = source,
    title = title,
    url = url,
    urlToImage = imageUrl
)

fun List<PopularNewDto>.toDomain() = this.map { it.toDomain() }

private val PopularNewDto.imageUrl: String get() {
    return media
        ?.firstOrNull()
        ?.mediaMetadata
        ?.firstOrNull { it.format == "mediumThreeByTwo440" }
        ?.url ?: media
            ?.firstOrNull()
            ?.mediaMetadata
            ?.firstOrNull()
            ?.url.orEmpty()
}

fun PagingData<PopularNewDto>.toDomain() = this.map { it.toDomain() }