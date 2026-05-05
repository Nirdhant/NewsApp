package com.example.news_app.di

import com.example.news_app.core.data.networking.HttpClientFactory
import com.example.news_app.core.data.networking.UrlConstructor
import com.example.news_app.news.data.networking.RemoteNewsDataSource
import com.example.news_app.news.domain.NewsDataSource
import com.example.news_app.news.presentation.news_list.home_screen.NewsViewModel
import com.example.news_app.news.presentation.news_list.search_screen.SearchViewModel
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

}