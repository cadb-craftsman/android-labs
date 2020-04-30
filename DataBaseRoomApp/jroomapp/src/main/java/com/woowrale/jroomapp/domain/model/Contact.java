package com.woowrale.jroomapp.domain.model;

public class Contact {

    private String name;
    private String image;
    private String phone;
    private String email;

    public Contact(String name, String image, String phone, String email) {
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
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
