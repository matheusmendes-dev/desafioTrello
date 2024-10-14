package com.mendev.trello.helpers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object NetworkHelper {

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

}