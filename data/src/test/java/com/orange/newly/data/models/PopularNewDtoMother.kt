package com.orange.newly.data.models

class PopularNewDtoMother(
    val url: String = "https://example.com/pop",
    val title: String = "Pop new"
) {
    fun build() = PopularNewDto(
        url = url,
        title = title,
        abstract = "Abstract",
        byline = "By Author",
        publishedDate = "2025-11-27",
        source = "source",
        media = null
    )

    companion object {
        val list = listOf(
            PopularNewDtoMother(
                url = "https://example.com/top-1",
                title = "Top New 1"
            ).build(),
            PopularNewDtoMother(
                url = "https://example.com/top-2",
                title = "Top New 2"
            ).build()
        )
    }
}