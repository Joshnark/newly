package com.orange.newly.feature.models

import com.orange.newly.domain.models.New

class NewMotherObj(
    val id: String = "test-1",
    val title: String = "New title"
) {

    fun build() = New(
        id = id,
        title = title,
        author = "John Doe | Josh Doe | Juan Doe",
        content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        publishedAt = "12/12/1212",
        source = "source",
        url = "asd",
        urlToImage = "1243asdgfaf"
    )

    companion object {
        val single = NewMotherObj().build()


        val list = (0..20).map { index ->
            NewMotherObj(
                id = "test-$index",
                title = "test $index"
            ).build()
        }
    }
}