package com.example.news.news.presentation.news_list.home_screen

import com.example.news.core.domain.util.NetworkErrors

sealed interface NewsListEvent {
    data class Errors(val error: NetworkErrors): NewsListEvent
}