package com.example.news_app.core.data.networking

import com.example.news_app.core.domain.util.NetworkErrors
import com.example.news_app.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, NetworkErrors> {
    val response = try { execute() }
    catch(e: UnresolvedAddressException) {
        return Result.Error(NetworkErrors.NO_INTERNET)
    } catch(e: SerializationException) {
        return Result.Error(NetworkErrors.SERIALIZATION)
    } catch(e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkErrors.UNKNOWN)
    }

    return responseToResult(response)
}