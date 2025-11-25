package com.orange.newly.data.mappers

import androidx.paging.PagingData
import androidx.paging.map
import com.orange.newly.data.models.NewDto
import com.orange.newly.data.models.NewEntity
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New

fun NewDto.toEntities(category: Category?) = NewEntity(
    title = this.title,
    author = this.author,
    content = this.content,
    description = this.description,
    publishedAt = this.publishedAt,
    source = this.source.name,
    url = this.url,
    urlToImage = this.urlToImage,
    category = category
)

fun List<NewDto>.toEntities(category: Category? = null) = this.map { it.toEntities(category) }

fun NewDto.toDomain() = New(
    title = this.title,
    author = this.author,
    content = this.content,
    description = this.description,
    publishedAt = this.publishedAt,
    source = this.source.name,
    url = this.url,
    urlToImage = this.urlToImage,
)


fun PagingData<NewDto>.toDomain() = this.map { it.toDomain() }