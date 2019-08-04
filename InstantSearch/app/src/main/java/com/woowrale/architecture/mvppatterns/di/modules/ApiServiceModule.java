package com.woowrale.architecture.mvppatterns.di.modules;

import com.woowrale.architecture.mvppatterns.data.repository.ApiClient;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiServiceModule {

    private final ApiClient apiClient;

    public ApiServiceModule(ApiClient apiClient){
        this.apiClient = apiClient;
    }

    @Provides
    ApiClient getApiClient(){
        return  apiClient;
    }

}


