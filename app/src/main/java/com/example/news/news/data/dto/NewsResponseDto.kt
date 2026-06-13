package com.example.news.news.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    val articles: List<NewsDto>
)