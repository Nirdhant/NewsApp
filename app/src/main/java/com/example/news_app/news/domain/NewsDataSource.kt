package com.example.news_app.news.domain

import com.example.news_app.core.domain.util.NetworkErrors
import com.example.news_app.core.domain.util.Result

interface NewsDataSource {
    suspend fun getNews(): Result<List<News> ,NetworkErrors>
    suspend fun searchNews(query:String): Result<List<News> ,NetworkErrors>
}