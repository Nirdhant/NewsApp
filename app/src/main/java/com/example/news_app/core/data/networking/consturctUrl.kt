package com.example.news_app.core.data.networking

import com.example.news_app.BuildConfig

class UrlConstructor{
    fun constructUrl(endpoint:String): String{
        return when{
            endpoint.contains(BuildConfig.BASE_URL)->"${endpoint}everything?domains=techcrunch.com,thenextweb.com,engadget.com&apiKey=${BuildConfig.API_KEY}"
            endpoint.startsWith("/")->"${BuildConfig.BASE_URL}${endpoint.drop(1)}?domains=techcrunch.com,thenextweb.com,engadget.com&apiKey=${BuildConfig.API_KEY}"
            else-> "${BuildConfig.BASE_URL}$endpoint?domains=techcrunch.com,thenextweb.com,engadget.com&apiKey=${BuildConfig.API_KEY}"
        }
    }
    fun constructQuery(query: String): String {
        return "${BuildConfig.BASE_URL}everything?domains=techcrunch.com,thenextweb.com,engadget.com&q=${query}&apiKey=${BuildConfig.API_KEY}"
    }
}
