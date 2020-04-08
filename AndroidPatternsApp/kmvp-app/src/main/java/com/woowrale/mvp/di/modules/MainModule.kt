package com.woowrale.mvp.di.modules

import android.content.Context
import com.woowrale.mvp.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(val mainApplication: MainApplication) {

    init {
        val mainApplication: MainApplication
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mainApplication
    }
}
