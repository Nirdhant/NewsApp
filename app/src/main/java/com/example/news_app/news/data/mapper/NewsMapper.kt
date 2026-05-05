package com.example.news_app.news.data.mapper

import com.example.news_app.news.data.dto.NewsDto
import com.example.news_app.news.domain.News

fun NewsDto.toNews(): News {
    return News(
        id = url.hashCode().let { if (it<0) -it else it },
        author = author?:"NA",
        title = title?:"No Title",
        description = description?:"No Description",
        url = url?:"NA",
        image = urlToImage?:"No Image",
        publishedAt = publishedAt?:"NA",
        content = content?:"No Content"
    )
}