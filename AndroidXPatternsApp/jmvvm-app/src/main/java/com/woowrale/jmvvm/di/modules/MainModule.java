package com.woowrale.jmvvm.di.modules;

import android.content.Context;

import com.woowrale.jmvvm.MainApplication;

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
    Context provideContext(){
        return mainApplication;
    }

}
