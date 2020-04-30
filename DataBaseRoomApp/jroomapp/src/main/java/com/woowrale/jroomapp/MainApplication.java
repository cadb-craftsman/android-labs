package com.woowrale.jroomapp;

import android.app.Application;

import com.woowrale.jroomapp.di.components.DaggerMainComponent;
import com.woowrale.jroomapp.di.components.MainComponent;
import com.woowrale.jroomapp.di.modules.MainModule;

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
