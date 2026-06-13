package com.example.news.news.presentation.news_list.search_screen

import com.example.news.core.domain.util.NetworkErrors


sealed interface SearchEvent {
    data class Errors(val error: NetworkErrors): SearchEvent
}