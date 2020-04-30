package com.woowrale.kroomapp.data.mappers

import com.woowrale.kroomapp.data.database.model.ContactEntity as ContactRoom
import com.woowrale.kroomapp.domain.model.Contact

fun Contact.toRoomContact(): ContactRoom = ContactRoom(id, name, image, phone, email)

fun ContactRoom.toContact(): Contact = Contact(id, name, image, phone, email)

