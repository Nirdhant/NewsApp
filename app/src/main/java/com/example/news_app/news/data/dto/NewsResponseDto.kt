package com.example.news_app.news.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    val articles: List<NewsDto>
)