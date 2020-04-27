package com.woowrale.data.repository.remote

import com.woowrale.domain.model.Contact

interface RemoteContactSource {
    fun getContacts(
        apiContacts: String,
        source: String,
        query: String
    ): List<Contact>
}

class RemoteRepository(private val remoteContactSource: RemoteContactSource) {
    fun getContacts(
        apiContacts: String,
        source: String,
        query: String
    ): List<Contact> {
        return remoteContactSource.getContacts(apiContacts, source, query)
    }
}
