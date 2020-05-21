package com.woowrale.jcleanarchitecture.data.local.datasource;

import com.woowrale.data.repository.local.LocalContactSource;
import com.woowrale.domain.model.Contact;
import com.woowrale.jcleanarchitecture.data.local.database.ContactDao;
import com.woowrale.jcleanarchitecture.data.local.database.ContactDatabase;
import com.woowrale.jcleanarchitecture.data.local.mappers.MapperFactory;

import java.util.List;

public class GetLocalContactSource implements LocalContactSource {

    private ContactDao contactDao;

    public GetLocalContactSource(ContactDatabase contactDatabase) {
        contactDao = contactDatabase.contactDao();
    }

    @Override
    public Boolean isEmpty() {
        boolean isEmpty = true;
        if (contactDao.contactCount() > 0) {
            isEmpty = false;
        }
        return isEmpty;
    }

    @Override
    public void saveContacts(List<Contact> contacts) {
        contactDao.insertContact(MapperFactory.mapperContactsToContactEntity(contacts));
    }

    @Override
    public List<Contact> getPopularContacts() {
        return MapperFactory.mapperContactsEntityToContact(contactDao.getAll());
    }

    @Override
    public Contact findById(Integer id) {
        return MapperFactory.mapperContactEntityToContact(contactDao.findById(id));
    }

    @Override
    public void update(Contact contact) {
        contactDao.updateContact(MapperFactory.mapperContactToContactEntity(contact));
    }
}
