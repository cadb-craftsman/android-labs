package com.woowrale.jcleanarchitecture.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class ContactResponse {
    String name;

    @SerializedName("image")
    String profileImage;

    String phone;
    String email;

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

    @Override
    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ContactResponse)) {
            return ((ContactResponse) obj).getEmail().equalsIgnoreCase(email);
        }
        return false;
    }
}
