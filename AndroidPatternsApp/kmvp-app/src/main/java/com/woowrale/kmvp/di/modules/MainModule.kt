package com.woowrale.kmvp.di.modules

import android.content.Context
import com.woowrale.kmvp.MainApplication
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
