package com.example.news_app.news.presentation.news_list.search_screen

import com.example.news_app.core.domain.util.NetworkErrors


sealed interface SearchEvent {
    data class Errors(val error: NetworkErrors): SearchEvent
}