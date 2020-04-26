package com.woowrale.data.repository.remote;

import com.woowrale.domain.model.Contact;

import java.util.List;

public interface RemoteContactSource {
    List<Contact> getContacts(String apiContacts, String source, String query);
}
