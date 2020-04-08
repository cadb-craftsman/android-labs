package com.woowrale.mvp.di.modules

import com.woowrale.mvp.data.repository.ApiClient
import com.woowrale.mvp.data.repository.ApiService
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
