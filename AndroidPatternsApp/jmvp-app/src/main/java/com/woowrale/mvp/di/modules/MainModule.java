package com.woowrale.mvp.di.modules;

import android.content.Context;
import com.woowrale.mvp.MainApplication;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

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
