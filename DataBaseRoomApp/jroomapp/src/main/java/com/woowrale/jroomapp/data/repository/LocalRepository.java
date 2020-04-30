package com.woowrale.jroomapp.data.repository;

import com.woowrale.jroomapp.data.datasource.LocalDataSource;
import com.woowrale.jroomapp.domain.model.Contact;

import java.util.List;

public class LocalRepository {

    private LocalDataSource localDataSource;

    public LocalRepository(LocalDataSource localDataSource){
        this.localDataSource = localDataSource;
    }

    public Contact findById(Integer id){
        return localDataSource.findById(id);
    }

    public List<Contact> getAll(){
        return localDataSource.getPopularContacts();
    }

    public void saveAll(List<Contact> contacts){
        localDataSource.saveContacts(contacts);
    }

    public void updateContact(Contact contact){
        localDataSource.update(contact);
    }
}
