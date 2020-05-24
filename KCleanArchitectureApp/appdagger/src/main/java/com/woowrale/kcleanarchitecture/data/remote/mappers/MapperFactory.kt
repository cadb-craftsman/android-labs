package com.woowrale.kcleanarchitecture.data.remote.mappers

import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.data.remote.model.response.ContactResponse

fun ContactResponse.toContact(): Contact = Contact(0, name, image, phone, email)