package com.orange.newly.data.mappers

import android.util.Base64
import com.orange.newly.data.models.NewEntity
import com.orange.newly.data.models.TopNewDto
import com.orange.newly.domain.models.New

fun TopNewDto.toEntity() = NewEntity(
    id = Base64.encodeToString(url.toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP),
    author = byline,
    content = abstract,
    description = abstract,
    publishedAt = publishedDate,
    title = title,
    url = url,
    urlToImage = imageUrl
)

fun List<TopNewDto>.toEntity() = this.map { it.toEntity() }

fun TopNewDto.toDomain() = New(
    author = byline,
    content = abstract,
    description = abstract,
    publishedAt = publishedDate,
    title = title,
    url = url,
    urlToImage = imageUrl
)

fun List<TopNewDto>.toDomain() = this.map { it.toDomain() }

private val TopNewDto.imageUrl: String get() {
    return multimedia
        ?.firstOrNull { it.format == "threeByTwoSmallAt2X" }
        ?.url ?: multimedia
            ?.firstOrNull()
            ?.url
            .orEmpty()
}