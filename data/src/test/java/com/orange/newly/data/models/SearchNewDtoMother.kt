package com.orange.newly.data.models

class SearchNewDtoMother(
    val url: String = "https://example.com/search",
    val title: String = "New"
) {
    fun build() = SearchNewDto(
        abstract = "Search result abstract",
        byline= Byline("By Author"),
        headline= Headline(title),
        publicationDate = "2025-11-27",
        webUrl = url,
        source = "NYT",
        multimedia = null
    )

    companion object {
        val list = (0 .. 20).map { index ->
            SearchNewDtoMother(
                url = "https://example.com/search-$index",
                title = "Search New $index"
            ).build()
        }
    }
}