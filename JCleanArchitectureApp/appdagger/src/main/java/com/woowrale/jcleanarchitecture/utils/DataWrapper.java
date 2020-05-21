package com.woowrale.jcleanarchitecture.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.woowrale.domain.model.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataWrapper {

    public List<Contact> fromJson(String s) {
        Type listType = new TypeToken<ArrayList<Contact>>() {}.getType();
        ArrayList<Contact> contacts = new Gson().fromJson(s, listType);
        return contacts;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public String getJsonFromAssets(Context context){
        AssetManager assetManager = context.getAssets();
        String json = "";

        try {
            InputStream stream  = assetManager.open("contacts.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static List<Contact> getContactsFromJson(Context context){
        DataWrapper wrapper = new DataWrapper();
        return wrapper.fromJson(wrapper.getJsonFromAssets(context));
    }
}
