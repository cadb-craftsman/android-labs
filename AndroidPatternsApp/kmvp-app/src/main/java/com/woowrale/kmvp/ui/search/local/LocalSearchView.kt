package com.woowrale.kmvp.ui.search.local

import com.woowrale.kmvp.data.model.Contact
import com.woowrale.kmvp.ui.adapters.ContactsAdapterFilterable
import com.woowrale.kmvp.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable

interface LocalSearchView: BasePresenter.View{

    fun getDisposable(): CompositeDisposable

    fun getmAdapter(): ContactsAdapterFilterable

    fun getContactsList(): List<Contact>

    fun setContactsList(contacts: List<Contact>)
}