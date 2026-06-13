package com.example.news.di

import com.example.news.core.data.networking.HttpClientFactory
import com.example.news.core.data.networking.UrlConstructor
import com.example.news.core.presentation.theme.ThemeRepository
import com.example.news.news.data.networking.RemoteNewsDataSource
import com.example.news.news.domain.NewsDataSource
import com.example.news.news.presentation.news_list.home_screen.NewsViewModel
import com.example.news.news.presentation.news_list.search_screen.SearchViewModel
import com.example.news.news.presentation.news_list.setting_screen.SettingViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module{
    single{
        HttpClientFactory.create(CIO.create())
    }
    single{ UrlConstructor() }
    singleOf(::RemoteNewsDataSource).bind<NewsDataSource>()

    viewModelOf(::NewsViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::SettingViewModel)

    single{ ThemeRepository() }


}