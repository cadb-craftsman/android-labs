package com.woowrale.rxkotlinretrofit.data.repository

import com.google.gson.GsonBuilder
import com.woowrale.rxkotlinretrofit.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ClientRetrofit {

    companion object {
        fun getRetrofit(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }
}

class ApiServices{

    companion object {
        fun service(): ApiServicesInterface {
            return ClientRetrofit.getRetrofit().create(ApiServicesInterface::class.java)
        }
    }
}