package com.example.news.news.presentation.news_list.home_screen

import com.example.news.news.presentation.news_list.model.NewsUI

sealed interface HomeScreenAction{
    data class OnNewsClick(val news: NewsUI): HomeScreenAction
}