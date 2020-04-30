package com.woowrale.jroomapp.data.datasource;

import com.woowrale.jroomapp.data.database.ContactDao;
import com.woowrale.jroomapp.data.database.ContactDatabase;
import com.woowrale.jroomapp.data.mappers.MapperFactory;
import com.woowrale.jroomapp.domain.model.Contact;

import java.util.List;

public class ContactDataSource implements LocalDataSource {

    private ContactDao contactDao;

    public ContactDataSource(ContactDatabase contactDatabase) {
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
