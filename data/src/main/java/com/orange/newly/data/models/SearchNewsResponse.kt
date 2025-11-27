package com.orange.newly.data.models

import com.google.gson.annotations.SerializedName

data class SearchNewsResponse(
    val status: String,
    val response: SearchResponseData
)

data class SearchResponseData(
    val docs: List<SearchNewDto>
)

data class SearchNewDto(
    val abstract: String?,
    val byline: Byline?,
    val headline: Headline?,
    @SerializedName("pub_data")
    val publicationDate: String?,
    @SerializedName("web_url")
    val webUrl: String,
    val source: String?,
    val multimedia: SearchMultimediaDto?
)

data class Byline(
    val original: String
)

data class Headline(
    val main: String
)

data class SearchMultimediaDto(
    val caption: String?,
    val credit: String?,
    val default: ImageData?,
    val thumbnail: ImageData?
)

data class ImageData(
    val url: String,
    val height: Int,
    val width: Int
)