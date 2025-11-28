package com.orange.newly.data.models

class TopNewDtoMother(
    val url: String = "https://example.com/top",
    val title: String = "Top new"
) {
    fun build() = TopNewDto(
        url = url,
        title = title,
        abstract = "Abstract",
        byline = "By Author",
        publishedDate = "2025-11-27",
        section = "Technology",
        subsection = "",
        multimedia = null
    )

    companion object {
        val list = listOf(
            TopNewDtoMother(
                url = "https://example.com/top-1",
                title = "Top New 1"
            ).build(),
            TopNewDtoMother(
                url = "https://example.com/top-2",
                title = "Top New 2"
            ).build()
        )
    }
}