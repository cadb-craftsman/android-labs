package com.woowrale.kroomapp.data.repository

import com.woowrale.kroomapp.data.datasource.LocalDataSource
import com.woowrale.kroomapp.domain.model.Contact

class LocalRepository (private val localDataSource: LocalDataSource) {

    fun findById(id: Int): Contact {
        return localDataSource.findById(id)
    }

    fun getAll(): List<Contact> {
        return localDataSource.getPopularContacts()
    }

    fun saveAll(contacts: List<Contact>) {
        localDataSource.saveContacts(contacts)
    }

    fun updateContact(contact: Contact) {
        localDataSource.update(contact)
    }
}