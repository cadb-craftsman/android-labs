package com.woowrale.kcleanarchitecture.data.local.datasource

import com.woowrale.data.repository.local.LocalContactSource
import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.data.local.database.ContactDao
import com.woowrale.kcleanarchitecture.data.local.database.ContactDatabase
import com.woowrale.kcleanarchitecture.data.local.mappers.toContact
import com.woowrale.kcleanarchitecture.data.local.mappers.toRoomContact

class GetLocalContactSource(contactDatabase: ContactDatabase) : LocalContactSource {

    private var contactDao: ContactDao = contactDatabase.contactDao()

    override fun getContacts(): List<Contact> {
        return contactDao.getAll().map { it.toContact() }
    }

    override fun isEmpty(): Boolean {
        return contactDao.contactCount() <= 0
    }

    override fun save(contacts: List<Contact>) {
        contactDao.insertContact(contacts.map { it.toRoomContact() })
    }

    override fun findById(id: Int): Contact {
        return contactDao.findById(id).toContact()
    }

    override fun update(contact: Contact) {
        contactDao.updateContact(contact.toRoomContact())
    }
}