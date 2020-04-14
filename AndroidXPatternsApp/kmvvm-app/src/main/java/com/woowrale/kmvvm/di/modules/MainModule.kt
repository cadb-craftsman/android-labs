package com.woowrale.kmvvm.di.modules

import android.content.Context
import com.woowrale.kmvvm.MainApplication
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
