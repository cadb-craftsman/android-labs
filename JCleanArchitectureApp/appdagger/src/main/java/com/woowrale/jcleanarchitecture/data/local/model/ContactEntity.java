package com.woowrale.jcleanarchitecture.data.local.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class ContactEntity {

    @PrimaryKey(autoGenerate = true) private Integer id;
    private String name;
    private String image;
    private String phone;
    private String email;

    @Ignore
    public ContactEntity(String name, String image, String phone, String email) {
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
    }

    public ContactEntity(Integer id, String name, String image, String phone, String email) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
