package com.woowrale.jmvp;

import android.app.Application;
import com.woowrale.jmvp.di.components.DaggerMainComponent;
import com.woowrale.jmvp.di.components.MainComponent;
import com.woowrale.jmvp.di.modules.MainModule;
import com.woowrale.jmvp.di.modules.ServicesModule;

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
