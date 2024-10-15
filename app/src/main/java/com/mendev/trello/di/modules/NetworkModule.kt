package com.mendev.trello.di.modules

import com.mendev.trello.BuildConfig
import com.mendev.trello.data.network.TrelloApi
import com.mendev.trello.data.network.interceptors.AuthInterceptor
import com.mendev.trello.helpers.getLoggingInterceptor
import com.mendev.trello.helpers.getOkHttpClientBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideTrelloApi(
        retrofit: Retrofit
    ): TrelloApi = retrofit.create(TrelloApi::class.java)

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClient = getOkHttpClientBuilder()
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.addInterceptor(authInterceptor)

        return okHttpClient.build()
    }

    @Provides
    fun provideLoggingInterceptor() = getLoggingInterceptor(BuildConfig.DEBUG)

}