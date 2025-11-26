package com.orange.newly.data.models

import com.google.gson.annotations.SerializedName

data class PopularNewsResponse(
    val status: String,
    @SerializedName("num_results")
    val numResults: Int,
    val results: List<PopularNewDto>
)

data class PopularNewDto(
    val url: String,
    val title: String,
    val abstract: String,
    val byline: String,
    @SerializedName("published_date")
    val publishedDate: String,
    val source: String,
    val media: List<TopMediaDto>?
)

data class TopMediaDto(
    val type: String,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadataDto>
)

data class MediaMetadataDto(
    val url: String,
    val format: String
)