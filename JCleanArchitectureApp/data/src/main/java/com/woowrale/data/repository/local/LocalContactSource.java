package com.woowrale.data.repository.local;

import com.woowrale.domain.model.Contact;

import java.util.List;

public interface LocalContactSource {
    List<Contact> getContacts(String apiContacts, String source, String query);
}
