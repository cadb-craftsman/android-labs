package com.woowrale.architecture.mvppatterns.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class MainApplicationModule {

    private final Application application;

    public MainApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext(){
        return application;
    }
}
