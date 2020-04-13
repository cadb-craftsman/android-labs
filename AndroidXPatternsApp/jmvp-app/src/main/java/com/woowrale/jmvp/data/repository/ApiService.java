package com.woowrale.jmvp.data.repository;

import com.woowrale.jmvp.data.model.Contact;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

import java.util.List;

public interface ApiService {

    @GET Single<List<Contact>> getContacts(@Url String apiContacts,
                                           @Query("source") String source,
                                           @Query("search") String query);

}
