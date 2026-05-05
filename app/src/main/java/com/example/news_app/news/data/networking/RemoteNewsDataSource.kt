package com.example.news_app.news.data.networking

import com.example.news_app.core.data.networking.UrlConstructor
import com.example.news_app.core.data.networking.safeCall
import com.example.news_app.core.domain.util.NetworkErrors
import com.example.news_app.core.domain.util.Result
import com.example.news_app.core.domain.util.map
import com.example.news_app.news.data.dto.NewsResponseDto
import com.example.news_app.news.data.mapper.toNews
import com.example.news_app.news.domain.News
import com.example.news_app.news.domain.NewsDataSource
import io.ktor.client.HttpClient

class RemoteNewsDataSource (private val httpClient: HttpClient,private val urlConstructor: UrlConstructor): NewsDataSource{
    override suspend fun getNews(): Result<List<News>, NetworkErrors> {
        return safeCall<NewsResponseDto> {
            httpClient.get(urlString = urlConstructor.constructUrl("everything"))
        }.map {listResponse->
            listResponse.articles.map { newsDto -> newsDto.toNews() }
        }
    }

    override suspend fun searchNews(query:String): Result<List<News>, NetworkErrors> {
        return safeCall<NewsResponseDto> {
            httpClient.get(urlString = urlConstructor.constructQuery(query))
        }.map { listResponse->
            listResponse.articles.map { newDto->newDto.toNews() }
        }
    }

}