package com.example.news_app.news.presentation.news_detail

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.news_app.news.presentation.news_list.model.NewsUI


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DetailScreen(news: NewsUI){
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(news.url)
            }
        },
        update = {webView->
            if (webView.url!=news.url){
                webView.loadUrl(news.url)
            }
        }
    )
}
