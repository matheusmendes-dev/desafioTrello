package com.mendev.trello.data.network

import com.mendev.trello.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .addQueryParameter("token", BuildConfig.API_TOKEN)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}