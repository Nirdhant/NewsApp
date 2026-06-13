package com.example.news.news.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt:String?,
    val content: String?
)