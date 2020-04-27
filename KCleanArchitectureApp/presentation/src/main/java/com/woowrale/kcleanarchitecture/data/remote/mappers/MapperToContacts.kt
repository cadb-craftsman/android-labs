package com.woowrale.kcleanarchitecture.data.remote.mappers

import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.data.remote.model.ContactResponse
import java.util.*
import kotlin.collections.ArrayList

class MapperToContacts {
    fun mappertoContactList(contacts: List<ContactResponse>): List<Contact> {
        val contactList: MutableList<Contact> = ArrayList<Contact>()
        if (contacts != null) {
            for (cr in contacts) {
                contactList.add(Contact(cr.name, cr.profileImage, cr.phone, cr.email))
            }
        }
        return contactList
    }
}