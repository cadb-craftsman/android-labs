package com.woowrale.kmvvm

import android.app.Application
import com.woowrale.kmvvm.di.components.DaggerMainComponent
import com.woowrale.kmvvm.di.components.MainComponent
import com.woowrale.kmvvm.di.modules.MainModule
import com.woowrale.kmvvm.di.modules.ServicesModule

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