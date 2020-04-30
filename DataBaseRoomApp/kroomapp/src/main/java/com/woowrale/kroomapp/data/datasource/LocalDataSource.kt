package com.woowrale.kroomapp.data.datasource

import com.woowrale.kroomapp.domain.model.Contact

interface LocalDataSource {

    fun isEmpty(): Boolean

    fun saveContacts(contacts: List<Contact>)

    fun getPopularContacts(): List<Contact>

    fun findById(id: Int): Contact

    fun update(contact: Contact)

}