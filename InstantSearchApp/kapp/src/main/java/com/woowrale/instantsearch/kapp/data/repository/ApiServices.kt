package com.woowrale.instantsearch.kapp.data.repository

import com.woowrale.instantsearch.kapp.data.model.Contact
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET
    fun getContacts(
        @Url apiContacts: String,
        @Query("source") source: String,
        @Query("search") query: String
    ): Single<List<Contact>>

}
