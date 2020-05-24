package com.woowrale.kcleanarchitecture.di.modules

import com.woowrale.data.repository.remote.RemoteContactSource
import com.woowrale.data.repository.remote.RemoteRepository
import com.woowrale.kcleanarchitecture.data.remote.datasource.GetRemoteContactSource
import com.woowrale.kcleanarchitecture.data.remote.server.ApiClient
import com.woowrale.kcleanarchitecture.data.remote.server.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiClient().getClient().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteContactSource(apiService: ApiService): RemoteContactSource {
        return GetRemoteContactSource(apiService)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(remoteContactSource: RemoteContactSource): RemoteRepository {
        return RemoteRepository(remoteContactSource)
    }
}