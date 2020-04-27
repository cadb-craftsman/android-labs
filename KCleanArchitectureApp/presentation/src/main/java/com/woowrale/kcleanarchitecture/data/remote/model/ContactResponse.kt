package com.woowrale.kcleanarchitecture.data.remote.model

import com.google.gson.annotations.SerializedName

class ContactResponse(
    var name: String,
    @SerializedName("image")
    var profileImage: String,
    var phone: String,
    var email: String
) {
    override fun equals(obj: Any?): Boolean {
        return if (obj != null && obj is ContactResponse) {
            obj.email.equals(email, ignoreCase = true)
        } else false
    }
}