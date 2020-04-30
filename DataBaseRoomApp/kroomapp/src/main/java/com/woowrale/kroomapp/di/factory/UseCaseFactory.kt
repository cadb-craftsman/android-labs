package com.woowrale.kroomapp.di.factory

import com.woowrale.kroomapp.data.repository.LocalRepository
import com.woowrale.kroomapp.usecases.ContactAllUseCase
import com.woowrale.kroomapp.usecases.threads.JobScheduler
import com.woowrale.kroomapp.usecases.threads.UIScheduler
import javax.inject.Inject

class UseCaseFactory @Inject constructor(
    private val repository: LocalRepository,
    private val uiScheduler: UIScheduler,
    private val jobScheduler: JobScheduler
) {

    fun getContactsUsesCases(): ContactAllUseCase {
        return ContactAllUseCase(repository, uiScheduler, jobScheduler)
    }

}