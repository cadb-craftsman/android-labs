package com.woowrale.instantsearch.kapp.data.repository

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.woowrale.instantsearch.kapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    //private val REQUEST_TIMEOUT = 60
    //lateinit private var retrofit: Retrofit
    //lateinit private var okHttpClient: OkHttpClient

    companion object {

        fun getClient(): Retrofit {

            initOkHttp()

            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient!!)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }

        private fun initOkHttp() {
            val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            httpClient.addInterceptor(interceptor)

            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader(BuildConfig.HEADER_ACCEPT, BuildConfig.HEADER_ACCEPT_VALUE)
                    .addHeader(BuildConfig.HEADER_REQUEST, BuildConfig.HEADER_REQUEST_VALUE)
                    .addHeader(BuildConfig.HEADER_CONTENT, BuildConfig.HEADER_CONTENT_VALUE)

                val request = requestBuilder.build()
                chain.proceed(request)
            }

            okHttpClient = httpClient.build()
        }


        lateinit var retrofit: Retrofit
        private val REQUEST_TIMEOUT = 60
        private var okHttpClient: OkHttpClient? = null
    }
}