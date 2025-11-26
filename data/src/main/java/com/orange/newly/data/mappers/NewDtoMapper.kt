package com.orange.newly.data.mappers

import androidx.paging.PagingData
import androidx.paging.map
import com.orange.newly.data.models.NewDto
import com.orange.newly.data.models.NewEntity
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New

fun NewDto.toEntities(category: Category?) = NewEntity(
    title = this.title,
    author = this.author.orEmpty(),
    content = this.content.orEmpty(),
    description = this.description.orEmpty(),
    publishedAt = this.publishedAt,
    source = this.source?.name.orEmpty(),
    url = this.url.orEmpty(),
    urlToImage = this.urlToImage.orEmpty(),
    category = category
)

fun List<NewDto>.toEntities(category: Category? = null) = this.map { it.toEntities(category) }

fun NewDto.toDomain() = New(
    title = this.title,
    author = this.author.orEmpty(),
    content = this.content.orEmpty(),
    description = this.description.orEmpty(),
    publishedAt = this.publishedAt,
    source = this.source?.name.orEmpty(),
    url = this.url.orEmpty(),
    urlToImage = this.urlToImage.orEmpty(),
)


fun PagingData<NewDto>.toDomain() = this.map { it.toDomain() }