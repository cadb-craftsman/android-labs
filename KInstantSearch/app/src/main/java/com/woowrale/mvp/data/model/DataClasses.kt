package com.woowrale.mvp.data.model

import com.google.gson.annotations.SerializedName

class Contact(
    val name: String = "",
    @SerializedName("image")
    val profileImage: String = "",
    val phone: String = "",
    val email: String = ""
){
    override fun equals(obj: Any?): Boolean {
        return if (obj != null && obj is Contact) {
            obj.email.equals(email, ignoreCase = true)
        } else false
    }
}