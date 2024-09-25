package com.example.apptest.api.model

import com.example.apptest.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {

    private fun <T> createApiService(okHttpClient: OkHttpClient, url: String, clazz: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(clazz)
    }

    fun createApiService(okHttpClient: OkHttpClient): ApiService {
        return createApiService(
            okHttpClient = okHttpClient,
            url = "https://api.themoviedb.org/3/",
            clazz = ApiService::class.java
        )
    }

    fun createOkHttpClient(
        headerInterceptor: Interceptor,
        loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()

            requestBuilder.addHeader("accept", "application/json")
            requestBuilder.addHeader(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MGIzMjBhMjk2MmEyODEyYzM2ZDFmNGJmZWU2Y2IyNSIsIm5iZiI6MTcyNDE1Mzg3Ny42MTcwODgsInN1YiI6IjYxODVmYWZiZDQ4Y2VlMDA2MmQyZTQ5OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.1OF4x9sf6p6gEzChm2xvSLd2_yi-osVt6dVqMz0PQYU"
            )

            chain.proceed(requestBuilder.build())
        }
    }

    fun getLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

}