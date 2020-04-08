package com.woowrale.instantsearch.kapp.ui.search.remote

import com.woowrale.instantsearch.kapp.data.model.Contact
import com.woowrale.instantsearch.kapp.ui.adapters.ContactsAdapter
import com.woowrale.instantsearch.kapp.ui.base.BasePresenter
import io.reactivex.subjects.PublishSubject

interface RemoteSearchView : BasePresenter.View {

    val publishSubject: PublishSubject<String>

    fun  setContactsList(contacts: List<Contact>)

    fun getmAdapter(): ContactsAdapter

}
