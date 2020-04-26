package com.woowrale.domain.model;

public class Contact {

    private String name;
    private String profileImage;
    private String phone;
    private String email;

    public Contact(String name, String profileImage, String phone, String email) {
        this.name = name;
        this.profileImage = profileImage;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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

    public String getProfileImage() {
        return profileImage;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

}
