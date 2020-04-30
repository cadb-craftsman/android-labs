package com.woowrale.kroomapp.data.datasource

import com.woowrale.kroomapp.data.database.ContactDao
import com.woowrale.kroomapp.data.database.ContactDatabase
import com.woowrale.kroomapp.data.database.model.ContactEntity
import com.woowrale.kroomapp.data.mappers.toContact
import com.woowrale.kroomapp.data.mappers.toRoomContact
import com.woowrale.kroomapp.domain.model.Contact

class ContactDataSource (contactDatabase: ContactDatabase): LocalDataSource {

    private var contactDao: ContactDao = contactDatabase.contactDao()

    override fun isEmpty(): Boolean {return contactDao.contactCount() <= 0 }

    override fun saveContacts(contacts: List<Contact>) {
        contactDao.insertContact(contacts.map { it.toRoomContact() })
    }

    override fun getPopularContacts(): List<Contact> {
       return contactDao.getAll().map { it.toContact() }
    }

    override fun findById(id: Int): Contact {
        return contactDao.findById(id).toContact()
    }

    override fun update(contact: Contact) {
        contactDao.updateContact(contact.toRoomContact())
    }
}