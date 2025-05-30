package com.example.news_app.core.data.networking

import com.example.news_app.core.domain.util.NetworkErrors
import com.example.news_app.core.domain.util.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, NetworkErrors> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            }
            catch(e: NoTransformationFoundException) {
                Result.Error(NetworkErrors.SERIALIZATION)
            }
        }
        408 -> Result.Error(NetworkErrors.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkErrors.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkErrors.SERVER_ERROR)
        else -> Result.Error(NetworkErrors.UNKNOWN)
    }
}