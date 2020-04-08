package com.woowrale.mvp.di.modules;

import com.woowrale.mvp.data.repository.ApiClient;
import com.woowrale.mvp.data.repository.ApiService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ServicesModule {

    @Provides
    @Singleton
    ApiService getApiService(){
        return new ApiClient().getClient().create(ApiService.class);
    }
}


