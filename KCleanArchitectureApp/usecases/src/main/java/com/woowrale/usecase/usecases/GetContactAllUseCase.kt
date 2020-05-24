package com.woowrale.usecase.usecases

import com.woowrale.data.repository.local.LocalRepository
import com.woowrale.domain.model.Contact
import com.woowrale.usecase.base.BaseUseCase
import com.woowrale.usecase.threads.JobScheduler
import com.woowrale.usecase.threads.UIScheduler
import io.reactivex.Single

class GetContactAllUseCase(private val localRepository: LocalRepository, uiScheduler: UIScheduler, jobScheduler: JobScheduler) : BaseUseCase<List<Contact>, GetContactAllUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<List<Contact>> {
        return Single.create {
            try {
                this.saveAllContacstCase(params.contacts)
                val contacts = getAllCase()
                it.onSuccess(contacts)
            } catch (exception: Exception) {
                if (!it.isDisposed) {
                    it.onError(exception)
                }
            }
        }
    }

    fun findByIdCase(id: Int): Contact {
        return localRepository.findById(id)
    }

    fun getAllCase(): List<Contact> {
        return localRepository.getContacts()
    }

    fun saveAllContacstCase(contacts: List<Contact>) {
        localRepository.save(contacts)
    }

    fun updateContactCase(contact: Contact) {
        localRepository.update(contact)
    }

    data class Params constructor(val contacts: List<Contact>)
}