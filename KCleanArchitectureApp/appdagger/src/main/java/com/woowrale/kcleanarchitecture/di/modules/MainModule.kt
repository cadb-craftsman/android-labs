package com.woowrale.kcleanarchitecture.di.modules

import android.content.Context
import com.woowrale.kcleanarchitecture.MainApplication
import com.woowrale.kcleanarchitecture.di.threads.JobThread
import com.woowrale.kcleanarchitecture.di.threads.UIThread
import com.woowrale.usecase.threads.JobScheduler
import com.woowrale.usecase.threads.UIScheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule constructor(private var mainApplication: MainApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mainApplication
    }

    @Provides
    @Singleton
    fun provideJobScheduler(jobThread: JobThread): JobScheduler {
        return jobThread
    }

    @Provides
    @Singleton
    fun provideUIScheduler(uiThread: UIThread): UIScheduler {
        return uiThread
    }
}
