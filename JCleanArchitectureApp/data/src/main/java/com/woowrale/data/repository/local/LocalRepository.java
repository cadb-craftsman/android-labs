package com.woowrale.data.repository.local;

import com.woowrale.domain.model.Contact;

import java.util.List;

public class LocalRepository {
    private LocalContactSource localContactSource;

    public LocalRepository(LocalContactSource localContactSource){
        this.localContactSource = localContactSource;
    }

    public Contact findById(Integer id){
        return localContactSource.findById(id);
    }

    public List<Contact> getAll(){
        return localContactSource.getPopularContacts();
    }

    public void saveAll(List<Contact> contacts){
        localContactSource.saveContacts(contacts);
    }

    public void updateContact(Contact contact){
        localContactSource.update(contact);
    }
}
