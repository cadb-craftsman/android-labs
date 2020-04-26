package com.woowrale.data.repository.remote;

import com.woowrale.domain.model.Contact;

import java.util.List;

public class RemoteRepository {

    private RemoteContactSource remoteContactSource;

    public RemoteRepository(RemoteContactSource remoteContactSource) {
        this.remoteContactSource = remoteContactSource;
    }

    public List<Contact> getContacts(String apiContacts, String source, String query) {
        return remoteContactSource.getContacts(apiContacts, source, query);
    }
}
