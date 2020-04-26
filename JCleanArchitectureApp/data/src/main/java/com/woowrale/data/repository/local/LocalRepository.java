package com.woowrale.data.repository.local;

import com.woowrale.domain.model.Contact;

import java.util.List;

public class LocalRepository implements LocalContactSource {
    @Override
    public List<Contact> getContacts(String apiContacts, String source, String query) {
        return null;
    }
}
