package com.orange.newly.data.models

import com.google.gson.annotations.SerializedName

data class TopNewsResponse(
    val status: String,
    val section: String,
    @SerializedName("num_results")
    val numResults: Int,
    val results: List<TopNewDto>
)

data class TopNewDto(
    val section: String,
    val subsection: String,
    val title: String,
    val abstract: String,
    val url: String,
    val byline: String,
    @SerializedName("published_date")
    val publishedDate: String,
    val multimedia: List<TopMultimediaDto>?
)

data class TopMultimediaDto(
    val url: String,
    val format: String,
    val type: String
)