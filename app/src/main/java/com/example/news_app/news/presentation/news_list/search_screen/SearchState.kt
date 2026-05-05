package com.example.news_app.news.presentation.news_list.search_screen

import androidx.compose.runtime.Immutable
import com.example.news_app.news.presentation.news_list.model.NewsUI

@Immutable
data class SearchState(
    val isLoading :Boolean = false,
    val news: List<NewsUI> = emptyList(),
    val searchQuery: String = ""
)