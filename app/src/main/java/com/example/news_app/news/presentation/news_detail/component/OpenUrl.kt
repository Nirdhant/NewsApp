package com.example.news_app.news.presentation.news_detail.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun openUrl(context:Context,urlString: String){
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
        context.startActivity(browserIntent)
    }
    catch (e: Exception){
        Toast.makeText(context,"Cannot Open Link: $e", Toast.LENGTH_LONG).show()
    }
}