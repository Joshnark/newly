package com.orange.newly.data.mappers

import android.util.Base64
import androidx.paging.PagingData
import androidx.paging.map
import com.orange.newly.data.models.NewEntity
import com.orange.newly.data.models.SearchNewDto
import com.orange.newly.domain.models.New

fun SearchNewDto.toEntity() = NewEntity(
    id = Base64.encodeToString(webUrl.toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP),
    author = byline?.original.orEmpty(),
    content = abstract,
    description = abstract,
    publishedAt = publicationDate,
    source = source,
    title = headline?.main.orEmpty(),
    url = webUrl,
    urlToImage = imageUrl
)


fun List<SearchNewDto>.toEntity() = this.map { it.toEntity() }

fun SearchNewDto.toDomain() = New(
    author = byline?.original.orEmpty(),
    content = abstract.orEmpty(),
    description = abstract.orEmpty(),
    publishedAt = publicationDate.orEmpty(),
    source = source,
    title = headline?.main.orEmpty(),
    url = webUrl,
    urlToImage = imageUrl
)

fun List<SearchNewDto>.toDomain() = this.map { it.toDomain() }

fun PagingData<SearchNewDto>.toDomain() = this.map { it.toDomain() }

private val SearchNewDto.imageUrl: String get() {
    return multimedia?.default?.url
        ?: multimedia?.thumbnail?.url.orEmpty()
}

