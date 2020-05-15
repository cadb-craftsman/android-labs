package com.woowrale.jcleanarchitecture.data.remote.mappers;

import com.woowrale.domain.model.Contact;
import com.woowrale.jcleanarchitecture.data.remote.model.ContactResponse;

import java.util.ArrayList;
import java.util.List;

public class MapperToContacts {

    public List<Contact> mappertoContactList(List<ContactResponse> contacts){
        List<Contact> contactList = new ArrayList<Contact>();
        if(contacts != null){
            for (ContactResponse cr: contacts) {
                contactList.add(new Contact(cr.getName(), cr.getProfileImage(), cr.getPhone(), cr.getEmail()));
            }
        }
        return contactList;
    }
}
