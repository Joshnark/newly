package com.orange.newly.data.mappers

import androidx.paging.PagingData
import androidx.paging.map
import com.orange.newly.data.models.NewEntity
import com.orange.newly.domain.models.New

fun NewEntity.toDomain() = New(
    title = this.title,
    author = this.author.orEmpty(),
    content = this.content.orEmpty(),
    description = this.description.orEmpty(),
    publishedAt = this.publishedAt.orEmpty(),
    source = this.source.orEmpty(),
    url = this.url,
    urlToImage = this.urlToImage,
)

fun List<NewEntity>.toDomain() = this.map { it.toDomain() }

fun PagingData<NewEntity>.toDomain() = this.map { it.toDomain() }