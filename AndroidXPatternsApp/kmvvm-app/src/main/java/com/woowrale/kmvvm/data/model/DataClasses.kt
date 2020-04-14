package com.woowrale.kmvvm.data.model

import com.google.gson.annotations.SerializedName

data class Contact (
    var name: String? = null,
    @SerializedName("image")
    var profileImage: String? = null,
    var phone: String? = null,
    var email: String? = null
) {
    override fun equals(obj: Any?): Boolean {
        return if (obj != null && obj is Contact) {
            obj.email!!.equals(email!!, ignoreCase = true)
        } else {
            return false
        }
    }
}

