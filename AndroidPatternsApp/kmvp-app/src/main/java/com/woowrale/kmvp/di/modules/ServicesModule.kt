package com.woowrale.kmvp.di.modules

import com.woowrale.kmvp.data.repository.ApiClient
import com.woowrale.kmvp.data.repository.ApiService
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
