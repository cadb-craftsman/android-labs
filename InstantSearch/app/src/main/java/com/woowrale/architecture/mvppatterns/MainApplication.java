package com.woowrale.architecture.mvppatterns;

import android.app.Application;

import com.woowrale.architecture.mvppatterns.data.repository.ApiClient;
import com.woowrale.architecture.mvppatterns.di.components.DaggerMainApplicationComponent;
import com.woowrale.architecture.mvppatterns.di.components.MainApplicationComponent;
import com.woowrale.architecture.mvppatterns.di.modules.ApiServiceModule;
import com.woowrale.architecture.mvppatterns.di.modules.MainApplicationModule;

public class MainApplication extends Application {

    private MainApplicationComponent mainApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //mainApplicationComponent = DaggerMainApplicationComponent.builder()
        //        .mainApplicationModule(new MainApplicationModule(this))
        //        .apiServiceModule(new ApiServiceModule(new ApiClient()))
        //        .build();
    }

    public MainApplicationComponent getMainApplicationComponent() {
        return mainApplicationComponent;
    }
}
