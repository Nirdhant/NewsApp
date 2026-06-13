package com.example.news.news.domain

import com.example.news.core.domain.util.NetworkErrors
import com.example.news.core.domain.util.Result

interface NewsDataSource {
    suspend fun getNews(): Result<List<News> ,NetworkErrors>
    suspend fun searchNews(query:String): Result<List<News> ,NetworkErrors>
}