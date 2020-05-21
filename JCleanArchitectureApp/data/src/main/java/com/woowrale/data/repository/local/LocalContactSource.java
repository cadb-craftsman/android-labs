package com.woowrale.data.repository.local;

import com.woowrale.domain.model.Contact;

import java.util.List;

public interface LocalContactSource {
    Boolean isEmpty();

    void saveContacts(List<Contact> contacts);

    List<Contact> getPopularContacts();

    Contact findById(Integer id);

    void update(Contact contact);
}
