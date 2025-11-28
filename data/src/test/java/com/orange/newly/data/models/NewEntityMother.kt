package com.orange.newly.data.models

class NewEntityMother(
    val id: String = "1234567890",
    val title: String = "TestTitle"
) {

    fun build() = NewEntity(
        id = id,
        title = title,
        author = "John Doe",
        content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        publishedAt = "12/12/1212",
        source = "source",
        url = "https://example.com",
        urlToImage = "https://example.com/image.jpg"
    )

    companion object {

        val list = (0 .. 20).map { index ->
            NewEntityMother(
                id = "test-$index",
                title = "test-$index"
            ).build()
        }

    }

}