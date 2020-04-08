package com.woowrale.mvp.ui.search.remote

import com.woowrale.mvp.data.model.Contact
import com.woowrale.mvp.ui.adapters.ContactsAdapter
import com.woowrale.mvp.ui.base.BasePresenter
import io.reactivex.subjects.PublishSubject

interface RemoteSearchView : BasePresenter.View {

    fun getmAdapter(): ContactsAdapter

    fun getContactsList(): List<Contact>

    fun setContactsList(contacts: List<Contact>)

    fun getPublishSubject(): PublishSubject<String>
}