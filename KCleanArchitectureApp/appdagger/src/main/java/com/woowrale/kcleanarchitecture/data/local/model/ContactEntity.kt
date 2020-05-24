package com.woowrale.kcleanarchitecture.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class  ContactEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val image: String,
    val phone: String,
    val email: String
)