package com.woowrale.kcleanarchitecture.di.factory

import com.woowrale.data.repository.local.LocalRepository
import com.woowrale.data.repository.remote.RemoteRepository
import com.woowrale.usecase.threads.JobScheduler
import com.woowrale.usecase.threads.UIScheduler
import com.woowrale.usecase.usecases.GetContactAllUseCase
import com.woowrale.usecase.usecases.GetContactsUseCase
import javax.inject.Inject

class UseCaseFactory @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val uiScheduler: UIScheduler,
    private val jobScheduler: JobScheduler
) {

    fun getRemoteContacts(): GetContactsUseCase {
       return GetContactsUseCase(remoteRepository, uiScheduler, jobScheduler)
    }

    fun getLocalContacts(): GetContactAllUseCase{
        return  GetContactAllUseCase(localRepository, uiScheduler, jobScheduler)
    }
}