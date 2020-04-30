package com.woowrale.kroomapp.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.woowrale.kroomapp.domain.model.Contact
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class DataWrapper {
    fun fromJson(s: String): List<Contact> {
        val listType = object : TypeToken<ArrayList<Contact?>?>() {}.type
        return Gson().fromJson<ArrayList<Contact>>(s, listType)
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

    fun getJsonFromAssets(context: Context): String {
        val assetManager = context.assets
        var json: String = ""
        try {
            val stream = assetManager.open("contacts.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            json = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

    companion object {
        fun getContactsFromJson(context: Context): ArrayList<Contact> {
            val wrapper = DataWrapper()
            return wrapper.fromJson(wrapper.getJsonFromAssets(context)) as ArrayList<Contact>
        }

    }

}