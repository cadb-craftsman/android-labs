package com.woowrale.kroomapp

import android.app.Application
import com.woowrale.kroomapp.di.components.DaggerMainComponent
import com.woowrale.kroomapp.di.components.MainComponent
import com.woowrale.kroomapp.di.modules.MainModule

class MainApplication : Application() {
    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .build()
    }

}