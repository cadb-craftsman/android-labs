package com.woowrale.mvp.ui.search.local

import com.woowrale.mvp.data.model.Contact
import com.woowrale.mvp.ui.base.BasePresenter
import com.woowrale.mvp.ui.adapters.ContactsAdapterFilterable
import io.reactivex.disposables.CompositeDisposable

interface LocalSearchView : BasePresenter.View {

    val disposable: CompositeDisposable

    fun setContactsList(contactList: List<Contact>)

    fun getContactsList():List<Contact>

    fun getmAdapter(): ContactsAdapterFilterable

}