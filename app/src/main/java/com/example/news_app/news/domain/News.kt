package com.example.news_app.news.domain

data class News(
    val id: Int,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val image: String,
    val publishedAt:String,
    val content: String
)