package com.woowrale.kcleanarchitecture.ui.model

import android.os.Parcel
import android.os.Parcelable

data class ContactUI(
    var name: String?,
    var image: String?,
    var phone: String?,
    var email: String?
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(phone)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactUI> {
        override fun createFromParcel(parcel: Parcel): ContactUI {
            return ContactUI(parcel)
        }

        override fun newArray(size: Int): Array<ContactUI?> {
            return arrayOfNulls(size)
        }
    }

}