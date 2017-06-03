package com.github.judrummer.techcrunchnews.data.api

import com.github.judrummer.techcrunchnews.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppRetrofit {

    val instance: Retrofit = Retrofit.Builder()
            .baseUrl(NEWSAPI_URL)
            .addConverterFactory(createGsonConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createHttpClient())
            .build()

    private fun createHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        return httpClient.build()
    }

    private fun createGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create()
        return GsonConverterFactory.create(gson)
    }
}


