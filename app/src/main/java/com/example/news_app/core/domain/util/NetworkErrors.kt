package com.example.news_app.core.domain.util

enum class NetworkErrors: Error{
    NO_INTERNET,
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN,
}