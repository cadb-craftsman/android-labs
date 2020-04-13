package com.woowrale.jmvp.di.modules;

import com.woowrale.jmvp.data.repository.ApiClient;
import com.woowrale.jmvp.data.repository.ApiService;
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


