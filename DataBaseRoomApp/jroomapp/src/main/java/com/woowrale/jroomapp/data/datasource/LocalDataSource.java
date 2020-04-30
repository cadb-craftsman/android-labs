package com.woowrale.jroomapp.data.datasource;

import com.woowrale.jroomapp.domain.model.Contact;

import java.util.List;

public interface LocalDataSource {

    Boolean isEmpty();

    void saveContacts(List<Contact> contacts);

    List<Contact> getPopularContacts();

    Contact findById(Integer id);

    void update(Contact contact);
}
