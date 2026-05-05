package com.example.news_app.news.presentation.news_list.model

import com.example.news_app.news.domain.News
import kotlinx.serialization.Serializable

@Serializable
data class NewsUI(
    val id: Int,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val image: String,
    val publishedAt:String,
    val content: String
)

fun News.toNewsUI(): NewsUI{
    return NewsUI(
        id = id,
        author = author,
        title = title,
        description = description,
        url = url,
        image = image,
        publishedAt = publishedAt,
        content = content,
    )
}