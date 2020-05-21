package com.woowrale.jcleanarchitecture.di.modules;

import android.content.Context;

import com.woowrale.data.repository.local.LocalContactSource;
import com.woowrale.data.repository.local.LocalRepository;
import com.woowrale.data.repository.remote.RemoteContactSource;
import com.woowrale.data.repository.remote.RemoteRepository;
import com.woowrale.jcleanarchitecture.MainApplication;
import com.woowrale.jcleanarchitecture.data.local.database.ContactDatabase;
import com.woowrale.jcleanarchitecture.data.local.datasource.GetLocalContactSource;
import com.woowrale.jcleanarchitecture.di.threads.JobThread;
import com.woowrale.jcleanarchitecture.data.remote.ws.ApiClient;
import com.woowrale.jcleanarchitecture.data.remote.ws.ApiService;
import com.woowrale.jcleanarchitecture.data.remote.server.GetRemoteContactSource;
import com.woowrale.jcleanarchitecture.di.threads.UIThread;
import com.woowrale.usecase.threads.JobScheduler;
import com.woowrale.usecase.threads.UIScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private final MainApplication mainApplication;

    public MainModule(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mainApplication;
    }

    @Provides
    @Singleton
    public ApiService provideApiService(){
        return new ApiClient().getClient().create(ApiService.class);
    }

    @Provides
    @Singleton
    public RemoteContactSource provideRemoteContactSource(ApiService apiService) {
        return new GetRemoteContactSource(apiService);
    }

    @Provides
    @Singleton
    public RemoteRepository provideRemoteRepository(RemoteContactSource remoteContactSource) {
        return new RemoteRepository(remoteContactSource);
    }

    @Provides
    @Singleton
    public ContactDatabase provideDataBase(Context context){
        return ContactDatabase.build(context);
    }

    @Provides
    public LocalContactSource provideContactDataSource(ContactDatabase contactDatabase) {
        return new GetLocalContactSource(contactDatabase);
    }

    @Provides
    public LocalRepository provideLocalRepository(LocalContactSource localDataSource) {
        return new LocalRepository(localDataSource);
    }

    @Provides
    @Singleton
    public JobScheduler provideJobScheduler(JobThread jobThread){
        return jobThread;
    }

    @Provides
    @Singleton
    public UIScheduler provideUIScheduler(UIThread uiThread){
        return uiThread;
    }


}
