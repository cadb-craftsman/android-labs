package com.woowrale.kmvp

import android.app.Application
import com.woowrale.kmvp.di.components.DaggerMainComponent
import com.woowrale.kmvp.di.components.MainComponent
import com.woowrale.kmvp.di.modules.MainModule
import com.woowrale.kmvp.di.modules.ServicesModule

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