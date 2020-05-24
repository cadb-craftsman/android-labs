package com.woowrale.kcleanarchitecture.di.modules

import android.content.Context
import com.woowrale.data.repository.local.LocalContactSource
import com.woowrale.data.repository.local.LocalRepository
import com.woowrale.kcleanarchitecture.data.local.database.ContactDatabase
import com.woowrale.kcleanarchitecture.data.local.datasource.GetLocalContactSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): ContactDatabase {
        return ContactDatabase.build(context)
    }

    @Provides
    fun provideLocalContactDataSource(contactDatabase: ContactDatabase): LocalContactSource {
        return GetLocalContactSource(contactDatabase)
    }

    @Provides
    fun provideLocalRepository(localDataSource: LocalContactSource): LocalRepository {
        return LocalRepository(localDataSource)
    }

}