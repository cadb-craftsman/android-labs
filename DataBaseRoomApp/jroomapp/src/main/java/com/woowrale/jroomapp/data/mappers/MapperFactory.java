package com.woowrale.jroomapp.data.mappers;

import com.woowrale.jroomapp.data.database.model.ContactEntity;
import com.woowrale.jroomapp.domain.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MapperFactory {

    public static List<ContactEntity> mapperContactsToContactEntity(List<Contact> contacts){
        List<ContactEntity> contactList = new ArrayList<ContactEntity>();
        if(contacts != null){
            for (Contact c: contacts) {
                contactList.add(new ContactEntity(c.getName(), c.getImage(), c.getPhone(), c.getEmail()));
            }
        }
        return contactList;
    }

    public static List<Contact> mapperContactsEntityToContact(List<ContactEntity> contacts){
        List<Contact> contactList = new ArrayList<Contact>();
        if(contacts != null){
            for (ContactEntity c: contacts) {
                contactList.add(new Contact(c.getName(), c.getImage(), c.getPhone(), c.getEmail()));
            }
        }
        return contactList;
    }

    public static Contact mapperContactEntityToContact(ContactEntity contactEntity){
        if(contactEntity != null){
            return new Contact(contactEntity.getName(), contactEntity.getImage(), contactEntity.getEmail(), contactEntity.getPhone());
        }
        return null;
    }

    public static ContactEntity mapperContactToContactEntity(Contact contact){
        if(contact != null){
            return new ContactEntity(contact.getName(), contact.getImage(), contact.getEmail(), contact.getPhone());
        }
        return null;
    }
}
