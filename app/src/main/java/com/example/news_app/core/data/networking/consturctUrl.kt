package com.example.news_app.core.data.networking

import com.example.news_app.BuildConfig

fun constructUrl(endpoint:String): String{
    return when{
        endpoint.contains(BuildConfig.BASE_URL)-> endpoint+"news?access_key="+ BuildConfig.API_KEY
        endpoint.startsWith("/")-> BuildConfig.BASE_URL+endpoint.drop(1)+"?access_key="+ BuildConfig.API_KEY
        else-> BuildConfig.BASE_URL+endpoint+"?access_key="+ BuildConfig.API_KEY
    }
}