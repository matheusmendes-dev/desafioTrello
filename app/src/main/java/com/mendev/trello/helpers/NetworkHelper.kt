package com.mendev.trello.helpers

import com.mendev.trello.data.network.exceptions.ApiException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

fun getOkHttpClientBuilder(
    readTimeout: Long = 1,
    writeTimeout: Long = 1,
    connectTimeout: Long = 1,
    timeUnit: TimeUnit = TimeUnit.MINUTES
): OkHttpClient.Builder {
    val httpClient = OkHttpClient.Builder()
    httpClient.readTimeout(readTimeout, timeUnit)
    httpClient.writeTimeout(writeTimeout, timeUnit)
    httpClient.connectTimeout(connectTimeout, timeUnit)

    return httpClient
}

fun getLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level =
        when (isDebug) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
    HttpLoggingInterceptor.Level.BODY
    return interceptor
}

suspend fun <T : Any> safeApiCall(
    apiCall: suspend () -> Response<T>
): T? {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()
        } else {
            throw ApiException.HttpException(response.code(), response.message())
        }
    } catch (e: IOException) {
        throw ApiException.NetworkException()
    } catch (e: Exception) {
        throw ApiException.UnknownException()
    }
}