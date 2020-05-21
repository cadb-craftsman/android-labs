package com.woowrale.jcleanarchitecture.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.woowrale.jcleanarchitecture.data.local.model.ContactEntity;

@Database(entities = {ContactEntity.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public static ContactDatabase build(Context context) {
        return Room.databaseBuilder(context, ContactDatabase.class, "contact-db").build();
    }

    public abstract ContactDao contactDao();
}
