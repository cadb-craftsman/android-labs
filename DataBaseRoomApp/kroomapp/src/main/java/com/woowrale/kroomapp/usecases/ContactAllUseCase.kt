package com.woowrale.kroomapp.usecases

import com.woowrale.kroomapp.data.repository.LocalRepository
import com.woowrale.kroomapp.domain.model.Contact
import com.woowrale.kroomapp.usecases.base.BaseUseCase
import com.woowrale.kroomapp.usecases.threads.JobScheduler
import com.woowrale.kroomapp.usecases.threads.UIScheduler
import io.reactivex.Single

class ContactAllUseCase(private val localRepository: LocalRepository, uiScheduler: UIScheduler, jobScheduler: JobScheduler) : BaseUseCase<List<Contact>, ContactAllUseCase.Params>(uiScheduler, jobScheduler) {

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
        return localRepository.getAll()
    }

    fun saveAllContacstCase(contacts: List<Contact>) {
        localRepository.saveAll(contacts)
    }

    fun updateContactCase(contact: Contact) {
        localRepository.updateContact(contact)
    }

    data class Params constructor(val contacts: List<Contact>)
}