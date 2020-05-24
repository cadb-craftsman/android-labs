package com.woowrale.data.repository.local

import com.woowrale.domain.model.Contact

interface LocalContactSource {

    fun getContacts(): List<Contact>

    fun isEmpty(): Boolean

    fun save(contacts: List<Contact>)

    fun findById(id: Int): Contact

    fun update(contact: Contact)
}

class LocalRepository(private val localContactSource: LocalContactSource) {
    fun getContacts(): List<Contact> {
        return localContactSource.getContacts()
    }

    fun isEmpty(): Boolean {
        return localContactSource.isEmpty()
    }

    fun save(contacts: List<Contact>){
        localContactSource.save(contacts)
    }

    fun findById(id: Int): Contact{
        return localContactSource.findById(id)
    }

    fun update(contact: Contact){
        localContactSource.update(contact)
    }


}