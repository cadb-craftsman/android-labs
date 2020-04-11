package com.woowrale.kmvp.ui.search.remote

import com.woowrale.kmvp.data.model.Contact
import com.woowrale.kmvp.ui.adapters.ContactsAdapter
import com.woowrale.kmvp.ui.base.BasePresenter
import io.reactivex.subjects.PublishSubject

interface RemoteSearchView : BasePresenter.View {

    fun getmAdapter(): ContactsAdapter

    fun getContactsList(): List<Contact>

    fun setContactsList(contacts: List<Contact>)

    fun getPublishSubject(): PublishSubject<String>
}