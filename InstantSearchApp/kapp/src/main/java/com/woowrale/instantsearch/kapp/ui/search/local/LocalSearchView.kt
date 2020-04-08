package com.woowrale.instantsearch.kapp.ui.search.local

import com.woowrale.instantsearch.kapp.data.model.Contact
import com.woowrale.instantsearch.kapp.ui.adapters.ContactsAdapterFilterable
import com.woowrale.instantsearch.kapp.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable

interface LocalSearchView : BasePresenter.View {

    val disposable: CompositeDisposable

    fun setContactsList(contactList: List<Contact>)

    fun getContactsList():List<Contact>

    fun getmAdapter(): ContactsAdapterFilterable

}