package com.woowrale.jcleanarchitecture.data.remote.ws;

import com.woowrale.jcleanarchitecture.data.remote.model.ContactResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Call<List<ContactResponse>> getContacts(@Url String apiContacts,
                                            @Query("source") String source,
                                            @Query("search") String query);

}
