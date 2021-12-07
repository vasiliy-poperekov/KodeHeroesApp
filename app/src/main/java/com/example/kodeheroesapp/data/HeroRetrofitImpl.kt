package com.example.kodeheroesapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

class HeroRetrofitImpl {
    private fun buildOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor { httpMessage ->
                    Timber.e(httpMessage)
                }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(buildOkHttpClient())
        .build()

    val heroApi: HeroApi
        get() = retrofit.create()

    companion object {
        private const val BASE_URL = "https://akabab.github.io/superhero-api/api/"
    }
}