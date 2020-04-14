package com.woowrale.kmvvm.di.modules

import com.woowrale.kmvvm.data.repository.ApiClient
import com.woowrale.kmvvm.data.repository.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServicesModule {

    @Provides
    @Singleton
    fun getApiServices():ApiService{
        return ApiClient.getclient().create(ApiService::class.java)
    }
}
