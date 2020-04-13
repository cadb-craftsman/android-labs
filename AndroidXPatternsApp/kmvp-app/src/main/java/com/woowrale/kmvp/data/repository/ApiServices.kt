package com.woowrale.kmvp.data.repository

import com.woowrale.kmvp.data.model.Contact
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiContacts {

    @GET
    fun getContacts(
        @Url apiContacts: String,
        @Query("source") source: String,
        @Query("search") query: String
    ): Single<List<Contact>>

}