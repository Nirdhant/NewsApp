package com.example.news_app.news.presentation.news_list.home_screen

import androidx.compose.runtime.Immutable
import com.example.news_app.news.presentation.news_list.model.NewsUI

@Immutable
data class NewsState(
    val isLoading :Boolean = false,
    val news: List<NewsUI> = emptyList(),
    val selectedNews: NewsUI? = null
)