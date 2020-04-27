package com.woowrale.kcleanarchitecture.data.remote.ws

import com.woowrale.kcleanarchitecture.data.remote.model.ContactResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getContacts(
        @Url apiContacts: String,
        @Query("source") source: String
    ): Call<List<ContactResponse>>
}
