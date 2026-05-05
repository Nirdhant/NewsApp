package com.example.news_app.core.presentation

import android.content.Context
import com.example.news_app.R
import com.example.news_app.core.domain.util.NetworkErrors

fun NetworkErrors.toMessageString(context: Context): String{
    val resId = when(this){
        NetworkErrors.NO_INTERNET -> R.string.error_no_internet
        NetworkErrors.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkErrors.SERVER_ERROR -> R.string.error_unknown
        NetworkErrors.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkErrors.SERIALIZATION -> R.string.error_serialization
        NetworkErrors.UNKNOWN -> R.string.error_unknown
    }
    return context.getString(resId)
}