package com.news.noticiasapp.models

data class News(
    val id: Int = 0,
    val title: String,
    val content: String,
    val author: String,
    val published_at: String
)
