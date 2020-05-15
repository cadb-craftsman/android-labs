package com.woowrale.kroomapp.di.modules

import android.content.Context
import com.woowrale.kroomapp.MainApplication
import com.woowrale.kroomapp.data.database.ContactDatabase
import com.woowrale.kroomapp.data.datasource.ContactDataSource
import com.woowrale.kroomapp.data.datasource.LocalDataSource
import com.woowrale.kroomapp.data.repository.LocalRepository
import com.woowrale.kroomapp.di.threads.JobThread
import com.woowrale.kroomapp.di.threads.UIThread
import com.woowrale.kroomapp.usecases.threads.JobScheduler
import com.woowrale.kroomapp.usecases.threads.UIScheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule constructor(private val mApplication: MainApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideDataBase(context: Context): ContactDatabase {
        return ContactDatabase.build(context)
    }

    @Provides
    fun provideContactDataSource(contactDatabase: ContactDatabase): LocalDataSource {
        return ContactDataSource(contactDatabase)
    }

    @Provides
    fun provideRepository(localDataSource: LocalDataSource): LocalRepository {
        return LocalRepository(localDataSource)
    }

    @Provides
    fun provideJobScheduler(jobThread: JobThread): JobScheduler {
        return jobThread
    }

    @Provides
    fun provideUIScheduler(uiThread: UIThread): UIScheduler {
        return uiThread
    }
}
