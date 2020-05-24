package com.woowrale.kcleanarchitecture.data.local.database

import androidx.room.*
import com.woowrale.kcleanarchitecture.data.local.model.ContactEntity

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): List<ContactEntity>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun findById(id: Int): ContactEntity

    @Query("SELECT COUNT(id) FROM contacts")
    fun contactCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContact(contacts: List<ContactEntity>)

    @Update
    fun updateContact(contact: ContactEntity)
}