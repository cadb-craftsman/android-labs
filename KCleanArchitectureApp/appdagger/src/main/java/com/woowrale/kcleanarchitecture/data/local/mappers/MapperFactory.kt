package com.woowrale.kcleanarchitecture.data.local.mappers

import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.data.local.model.ContactEntity as ContactRoom

fun Contact.toRoomContact(): ContactRoom = ContactRoom(id, name, image, phone, email)

fun ContactRoom.toContact(): Contact = Contact(id, name, image, phone, email)

