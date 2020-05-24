package com.woowrale.kcleanarchitecture.data.remote.datasource

import com.woowrale.data.repository.remote.RemoteContactSource
import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.BuildConfig
import com.woowrale.kcleanarchitecture.data.remote.mappers.toContact
import com.woowrale.kcleanarchitecture.data.remote.server.ApiService
import java.io.IOException

class GetRemoteContactSource(apiService: ApiService) : RemoteContactSource {

    private val apiService: ApiService = apiService

    override fun getContacts(
        source: String,
        query: String
    ): List<Contact> {
        return try {
            apiService.getContacts(BuildConfig.GET_CONTACTS, source).execute().body()!!.map{ it.toContact() }
        } catch (e: IOException) {
           return listOfNotNull()
        }
    }

}

