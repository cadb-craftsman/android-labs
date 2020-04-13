package com.woowrale.jmvvm.di.modules;

import com.woowrale.jmvvm.data.repository.ApiClient;
import com.woowrale.jmvvm.data.repository.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {

    @Provides
    @Singleton
    ApiService getApiService(){
        return new ApiClient().getClient().create(ApiService.class);
    }
}


