package com.woowrale.mvp

import android.app.Application
import com.woowrale.mvp.di.components.DaggerMainComponent
import com.woowrale.mvp.di.components.MainComponent
import com.woowrale.mvp.di.modules.MainModule
import com.woowrale.mvp.di.modules.ServicesModule

class MainApplication : Application() {

    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
            .mainModule(MainModule(this))
            .servicesModule(ServicesModule())
            .build()
    }

}