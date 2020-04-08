package com.woowrale.mvp;

import android.app.Application;
import com.woowrale.mvp.di.components.DaggerMainComponent;
import com.woowrale.mvp.di.components.MainComponent;
import com.woowrale.mvp.di.modules.MainModule;
import com.woowrale.mvp.di.modules.ServicesModule;

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
