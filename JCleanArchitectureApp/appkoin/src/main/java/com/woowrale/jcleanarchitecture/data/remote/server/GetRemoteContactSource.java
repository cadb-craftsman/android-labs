package com.woowrale.jcleanarchitecture.data.remote.server;

import com.woowrale.data.repository.remote.RemoteContactSource;
import com.woowrale.domain.model.Contact;
import com.woowrale.jcleanarchitecture.data.remote.mappers.MapperToContacts;
import com.woowrale.jcleanarchitecture.data.remote.ws.ApiService;

import java.io.IOException;
import java.util.List;

public class GetRemoteContactSource implements RemoteContactSource {

    private ApiService apiService;

    public GetRemoteContactSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public List<Contact> getContacts(String apiContacts, String source, String query) {
        try {
            return new MapperToContacts().mappertoContactList(apiService.getContacts(apiContacts, source, query).execute().body());
        } catch (IOException e) {
            return null;
        }
    }
}
