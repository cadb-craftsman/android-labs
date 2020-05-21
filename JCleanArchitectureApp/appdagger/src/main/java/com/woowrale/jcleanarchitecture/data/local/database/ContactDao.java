package com.woowrale.jcleanarchitecture.data.local.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.woowrale.jcleanarchitecture.data.local.model.ContactEntity;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts")
    public List<ContactEntity> getAll();

    @Query("SELECT * FROM contacts WHERE id = :id")
    public ContactEntity findById(Integer id);

    @Query("SELECT COUNT(id) FROM contacts")
    public Integer contactCount();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertContact(List<ContactEntity> contacts);

    @Update
    public void updateContact(ContactEntity contact);
}
