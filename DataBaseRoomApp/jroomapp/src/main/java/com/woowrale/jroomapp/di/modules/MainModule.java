package com.woowrale.jroomapp.di.modules;

import android.content.Context;

import com.woowrale.jroomapp.MainApplication;
import com.woowrale.jroomapp.data.database.ContactDatabase;
import com.woowrale.jroomapp.data.datasource.ContactDataSource;
import com.woowrale.jroomapp.data.datasource.LocalDataSource;
import com.woowrale.jroomapp.data.repository.LocalRepository;
import com.woowrale.jroomapp.di.threads.JobThread;
import com.woowrale.jroomapp.di.threads.UIThread;
import com.woowrale.jroomapp.usecases.threads.JobScheduler;
import com.woowrale.jroomapp.usecases.threads.UIScheduler;

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
    public ContactDatabase provideDataBase(Context context){
        return ContactDatabase.build(context);
    }

    @Provides
    @Singleton
    public LocalDataSource provideContactDataSource(ContactDatabase contactDatabase) {
        return new ContactDataSource(contactDatabase);
    }

    @Provides
    @Singleton
    public LocalRepository provideRepository(LocalDataSource localDataSource) {
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
