package com.woowrale.jcleanarchitecture.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactUI implements Parcelable {

    private String name;
    private String image;
    private String phone;
    private String email;

    public ContactUI(String name, String image, String phone, String email) {
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
    }

    protected ContactUI(Parcel in) {
        name = in.readString();
        image = in.readString();
        phone = in.readString();
        email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(phone);
        dest.writeString(email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContactUI> CREATOR = new Creator<ContactUI>() {
        @Override
        public ContactUI createFromParcel(Parcel in) {
            return new ContactUI(in);
        }

        @Override
        public ContactUI[] newArray(int size) {
            return new ContactUI[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
