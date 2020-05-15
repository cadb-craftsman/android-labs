package com.woowrale.jcleanarchitecture;

import android.app.Application;

import com.woowrale.jcleanarchitecture.di.components.DaggerMainComponent;
import com.woowrale.jcleanarchitecture.di.components.MainComponent;
import com.woowrale.jcleanarchitecture.di.modules.MainModule;


public class MainApplication extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();

    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
