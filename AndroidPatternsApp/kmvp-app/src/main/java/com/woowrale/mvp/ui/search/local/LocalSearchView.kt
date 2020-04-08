package com.woowrale.mvp.ui.search.local

import com.woowrale.mvp.data.model.Contact
import com.woowrale.mvp.ui.adapters.ContactsAdapterFilterable
import com.woowrale.mvp.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable

interface LocalSearchView: BasePresenter.View{

    fun getDisposable(): CompositeDisposable

    fun getmAdapter(): ContactsAdapterFilterable

    fun getContactsList(): List<Contact>

    fun setContactsList(contacts: List<Contact>)
}