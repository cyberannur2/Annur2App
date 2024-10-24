package com.miniawradsantri.awrad.model

data class Article(
    val id: Int,
    val title: Title,
    val categories: List<Int>,
    val featured_media: Int,
    val date: String,
    val content: Content,
    val link: String
//    val categories_map: Map<Int, String>
)

data class Title(
    val rendered: String
)

data class Media(
    val source_url: String
)


data class Category(
    val id: Int,
    val name: String
)
data class Content(
    val rendered: String
)

