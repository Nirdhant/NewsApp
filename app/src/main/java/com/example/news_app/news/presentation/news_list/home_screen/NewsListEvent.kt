package com.example.news_app.news.presentation.news_list.home_screen

import com.example.news_app.core.domain.util.NetworkErrors

sealed interface NewsListEvent {
    data class Errors(val error: NetworkErrors): NewsListEvent
}