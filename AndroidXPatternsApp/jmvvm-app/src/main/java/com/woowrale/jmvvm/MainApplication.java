package com.woowrale.jmvvm;

import android.app.Application;

import com.woowrale.jmvvm.di.components.DaggerMainComponent;
import com.woowrale.jmvvm.di.components.MainComponent;
import com.woowrale.jmvvm.di.modules.MainModule;
import com.woowrale.jmvvm.di.modules.ServicesModule;

public class MainApplication extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .servicesModule(new ServicesModule())
                .build();

    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
